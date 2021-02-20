package com.kotlin.andi.cinema.data

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kotlin.andi.cinema.data.source.local.LocalDataSource
import com.kotlin.andi.cinema.data.source.remote.ApiResponse
import com.kotlin.andi.cinema.data.source.remote.RemoteDataSource
import com.kotlin.andi.cinema.data.source.remote.response.ResultsMovies
import com.kotlin.andi.cinema.data.source.remote.response.ResultsTV
import com.kotlin.andi.cinema.domain.model.Movies
import com.kotlin.andi.cinema.domain.model.MoviesFav
import com.kotlin.andi.cinema.domain.model.TV
import com.kotlin.andi.cinema.domain.model.TVFav
import com.kotlin.andi.cinema.domain.repository.IMoviesRepository
import com.kotlin.andi.cinema.utils.AppExecutors
import com.kotlin.andi.cinema.utils.DataMapper
import com.kotlin.andi.cinema.vo.Resource
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMoviesRepository {

    companion object {
        const val SIZE = 5

        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors,
        ): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteDataSource, localData, appExecutors)
            }
    }

    override fun getAllMovies(): LiveData<Resource<PagedList<Movies>>> =
        object : NetworkBoundResource<PagedList<Movies>, List<ResultsMovies>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<Movies>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(SIZE)
                    .setPageSize(SIZE)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovie().map {
                    DataMapper.mapEntitiesToMoviesDomain(it)
                }, config).build()
            }

            override fun shouldFetch(data: PagedList<Movies>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<MutableLiveData<List<ResultsMovies>>>> =
                remoteDataSource.getMovies()

            override fun saveCallResult(data: MutableLiveData<List<ResultsMovies>>) {
                Handler(Looper.getMainLooper()).post {
                    data.observeForever { response ->
                       val movieList = DataMapper.mapResponseToMovieEntities(response)
                        appExecutors.diskIO().execute {
                            localDataSource.addMovies(movieList)
                        }
                    }
                }
            }
        }.asLiveData()


    override fun getAllTVShows(): LiveData<Resource<PagedList<TV>>> =
        object : NetworkBoundResource<PagedList<TV>, List<ResultsTV>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TV>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(SIZE)
                    .setPageSize(SIZE)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTVShows().map {
                    DataMapper.mapEntitiesToTVDomain(it)
                }, config).build()
            }

            override fun shouldFetch(data: PagedList<TV>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<MutableLiveData<List<ResultsTV>>>> =
                remoteDataSource.getTVShows()

            override fun saveCallResult(data: MutableLiveData<List<ResultsTV>>) {
                Handler(Looper.getMainLooper()).post {
                    data.observeForever { response ->
                        val tvList = DataMapper.mapResponseToTVEntities(response)
                        appExecutors.diskIO().execute {
                            localDataSource.addTVShows(tvList)
                        }
                    }
                }
            }
        }.asLiveData()


    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    // Insert
    override fun addFavMovies(moviesFav: MoviesFav) {
        val data = DataMapper.mapDomainToMoviesFavEntities(moviesFav)
        executorService.execute {
            localDataSource.addFavMovies(data)
        }
    }

    override fun addFavTV(tvFav: TVFav) {
        val data = DataMapper.mapDomainToTVFavEntities(tvFav)
        executorService.execute {
            localDataSource.addFavTV(data)
        }
    }

    override fun readFavMovies(): LiveData<PagedList<MoviesFav>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(SIZE)
            .setPageSize(SIZE)
            .build()
        return LivePagedListBuilder(localDataSource.readFavMovies().map {
            DataMapper.mapEntitiesToMoviesFavDomain(it)
        }, config).build()
    }

    override fun readFavTV(): LiveData<PagedList<TVFav>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(SIZE)
            .setPageSize(SIZE)
            .build()
        return LivePagedListBuilder(localDataSource.readFavTV().map {
            DataMapper.mapEntitiesToTVFavDomain(it)
        }, config).build()
    }

    // Check
    override fun checkFavMovies(movieId: String): LiveData<List<MoviesFav>> =
        Transformations.map(localDataSource.checkFavMovies(movieId)) {
            DataMapper.mapCheckMovies(it)
        }

    override fun checkFavTV(tvId: String): LiveData<List<TVFav>> =
        Transformations.map(localDataSource.checkFavTV(tvId)) {
            DataMapper.mapCheckTV(it)
        }

    // Delete
    override fun deleteFavMovies(moviesFav: MoviesFav) {
        val data = DataMapper.mapDomainToMoviesFavEntities(moviesFav)
        executorService.execute {
            localDataSource.deleteMovies(data)
        }
    }

    override fun deleteTVMovies(tvFav: TVFav) {
        val data = DataMapper.mapDomainToTVFavEntities(tvFav)
        executorService.execute {
            localDataSource.deleteTVShows(data)
        }
    }
}
