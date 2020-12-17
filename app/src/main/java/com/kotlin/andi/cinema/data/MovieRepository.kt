package com.kotlin.andi.cinema.data

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kotlin.andi.cinema.data.source.local.LocalDataSource
import com.kotlin.andi.cinema.data.source.local.entity.MoviesEntity
import com.kotlin.andi.cinema.data.source.local.entity.PopularEntity
import com.kotlin.andi.cinema.data.source.local.entity.TVEntity
import com.kotlin.andi.cinema.data.source.local.entity.favorite.MoviesFavEntity
import com.kotlin.andi.cinema.data.source.local.entity.favorite.TVFavEntity
import com.kotlin.andi.cinema.data.source.remote.ApiResponse
import com.kotlin.andi.cinema.data.source.remote.RemoteDataSource
import com.kotlin.andi.cinema.data.source.remote.response.ResultsMovies
import com.kotlin.andi.cinema.data.source.remote.response.ResultsPopular
import com.kotlin.andi.cinema.data.source.remote.response.ResultsTV
import com.kotlin.andi.cinema.utils.AppExecutors
import com.kotlin.andi.cinema.vo.Resource
import java.util.concurrent.*

class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieDataSource {

    companion object {
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

    override fun getAllMovies(): LiveData<Resource<List<MoviesEntity>>> {
        return object :
            NetworkBoundResource<List<MoviesEntity>, List<ResultsMovies>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<MoviesEntity>> =
                localDataSource.getAllMovie()

            override fun shouldFetch(data: List<MoviesEntity>?): Boolean =
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

    override fun getAllTVShows(): LiveData<Resource<List<TVEntity>>> {
        return object : NetworkBoundResource<List<TVEntity>, List<ResultsTV>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<TVEntity>> =
                localDataSource.getAllTVShows()

            override fun shouldFetch(data: List<TVEntity>?): Boolean =
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

    override fun getAllPopular(): LiveData<Resource<List<PopularEntity>>> {
        return object : NetworkBoundResource<List<PopularEntity>, List<ResultsPopular>>(appExecutors){
            override fun loadFromDB(): LiveData<List<PopularEntity>> =
                localDataSource.getAllPopular()

            override fun shouldFetch(data: List<PopularEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<MutableLiveData<List<ResultsPopular>>>> =
                remoteDataSource.getPopular()

            override fun saveCallResult(data: MutableLiveData<List<ResultsPopular>>) {
                Handler(Looper.getMainLooper()).post {
                    data.observeForever { response ->
                        val popularList = ArrayList<PopularEntity>()
                        response.forEach {
                            val popular = PopularEntity(
                                it.id,
                                it.overview,
                                it.posterPath,
                                it.backdropPath,
                                it.name,
                                it.title,
                                it.voteAverage,
                                it.language,
                                it.mediaType
                            )
                            popularList.add(popular)
                        }
                        appExecutors.diskIO().execute {
                            localDataSource.addPopular(popularList)
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

    override fun readFavMovies(): LiveData<List<MoviesFavEntity>> =
        localDataSource.readFavMovies()

    override fun readFavTV(): LiveData<List<TVFavEntity>> =
        localDataSource.readFavTV()

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
