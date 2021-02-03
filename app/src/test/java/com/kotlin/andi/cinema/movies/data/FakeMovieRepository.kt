package com.kotlin.andi.cinema.movies.data

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kotlin.andi.cinema.data.source.local.entity.MoviesEntity
import com.kotlin.andi.cinema.data.source.local.entity.TVEntity
import com.kotlin.andi.cinema.data.MovieDataSource
import com.kotlin.andi.cinema.data.NetworkBoundResource
import com.kotlin.andi.cinema.data.source.local.LocalDataSource
import com.kotlin.andi.cinema.data.source.local.entity.favorite.MoviesFavEntity
import com.kotlin.andi.cinema.data.source.local.entity.favorite.TVFavEntity
import com.kotlin.andi.cinema.data.source.remote.ApiResponse
import com.kotlin.andi.cinema.data.source.remote.RemoteDataSource
import com.kotlin.andi.cinema.data.source.remote.response.ResultsMovies
import com.kotlin.andi.cinema.data.source.remote.response.ResultsTV
import com.kotlin.andi.cinema.utils.AppExecutors
import com.kotlin.andi.cinema.vo.Resource
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FakeMovieRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieDataSource {

    override fun getAllMovies(): LiveData<Resource<PagedList<MoviesEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MoviesEntity>, List<ResultsMovies>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MoviesEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(5)
                    .setPageSize(5)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovie(), config).build()
            }

            override fun shouldFetch(data: PagedList<MoviesEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<MutableLiveData<List<ResultsMovies>>>> =
                remoteDataSource.getMovies()

            override fun saveCallResult(data: MutableLiveData<List<ResultsMovies>>) {
                Handler(Looper.getMainLooper()).post {
                    data.observeForever { response ->
                        val movieList = ArrayList<MoviesEntity>()
                        response.forEach {
                            val movie = MoviesEntity(
                                it.id,
                                it.overview,
                                it.posterPath,
                                it.backdropPath,
                                it.name,
                                it.title,
                                it.voteAverage,
                                it.language
                            )
                            movieList.add(movie)
                        }
                        appExecutors.diskIO().execute {
                            localDataSource.addMovies(movieList)
                        }
                    }
                }
            }
        }.asLiveData()
    }

    override fun getAllTVShows(): LiveData<Resource<PagedList<TVEntity>>> {
        return object : NetworkBoundResource<PagedList<TVEntity>, List<ResultsTV>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TVEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(5)
                    .setPageSize(5)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTVShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TVEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<MutableLiveData<List<ResultsTV>>>> =
                remoteDataSource.getTVShows()

            override fun saveCallResult(data: MutableLiveData<List<ResultsTV>>) {
                Handler(Looper.getMainLooper()).post {
                    data.observeForever { response ->
                        val tvList = ArrayList<TVEntity>()
                        response.forEach {
                            val tv = TVEntity(
                                it.id,
                                it.overview,
                                it.posterPath,
                                it.backdropPath,
                                it.name,
                                it.voteAverage,
                                it.language
                            )
                            tvList.add(tv)
                        }
                        appExecutors.diskIO().execute {
                            localDataSource.addTVShows(tvList)
                        }
                    }
                }
            }
        }.asLiveData()
    }

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    // Insert
    override fun addFavMovies(moviesFavEntity: MoviesFavEntity) {
        executorService.execute {
            localDataSource.addFavMovies(moviesFavEntity)
        }
    }

    override fun addFavTV(tvFavEntity: TVFavEntity) {
        executorService.execute {
            localDataSource.addFavTV(tvFavEntity)
        }
    }

    override fun readFavMovies(): LiveData<PagedList<MoviesFavEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(localDataSource.readFavMovies(), config).build()
    }

    override fun readFavTV(): LiveData<PagedList<TVFavEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(localDataSource.readFavTV(), config).build()
    }

    // Check
    override fun checkFavMovies(movieId: String): LiveData<List<MoviesFavEntity>> =
        localDataSource.checkFavMovies(movieId)

    override fun checkFavTV(tvId: String): LiveData<List<TVFavEntity>> =
        localDataSource.checkFavTV(tvId)

    // Delete
    override fun deleteFavMovies(moviesFavEntity: MoviesFavEntity) {
        executorService.execute {
            localDataSource.deleteMovies(moviesFavEntity)
        }
    }

    override fun deleteTVMovies(tvFavEntity: TVFavEntity) {
        executorService.execute {
            localDataSource.deleteTVShows(tvFavEntity)
        }
    }
}
