package com.kotlin.andi.cinema.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kotlin.andi.cinema.data.MoviesEntity
import com.kotlin.andi.cinema.data.PopularEntity
import com.kotlin.andi.cinema.data.TVEntity
import com.kotlin.andi.cinema.data.source.remote.RemoteDataSource
import com.kotlin.andi.cinema.data.source.remote.RemoteDataSource.*
import com.kotlin.andi.cinema.data.source.remote.response.ResultsMovies
import com.kotlin.andi.cinema.data.source.remote.response.ResultsPopular
import com.kotlin.andi.cinema.data.source.remote.response.ResultsTV

class MovieRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    MovieDataSource {

    companion object {

        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteDataSource)
            }
    }

    override fun getAllMovies(): LiveData<List<MoviesEntity>> {
        val moviesResult = MutableLiveData<List<MoviesEntity>>()
        remoteDataSource.getMovies(object : LoadMovieCallback {
            override fun onAllMoviesReceived(movieResponses: LiveData<List<ResultsMovies>>) {
                movieResponses.observeForever { response ->
                    val movieList = ArrayList<MoviesEntity>()
                    response?.forEach {
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
                    moviesResult.postValue(movieList)
                }
            }
        })
        return moviesResult
    }

    override fun getAllTVShows(): LiveData<List<TVEntity>> {
        val tvResult = MutableLiveData<List<TVEntity>>()
        remoteDataSource.getTVShows(object : LoadTVCallback {
            override fun onAllTVShowsReceived(TVResponses: LiveData<List<ResultsTV>>) {
                TVResponses.observeForever { response ->
                    val tvList = ArrayList<TVEntity>()
                    response?.forEach {
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
                    tvResult.postValue(tvList)
                }
            }
        })
        return tvResult
    }

    override fun getAllPopular(): LiveData<List<PopularEntity>> {
        val popularResult = MutableLiveData<List<PopularEntity>>()
        remoteDataSource.getPopular(object : LoadPopularCallback {
            override fun onAllPopularReceived(PopularResponses: LiveData<List<ResultsPopular>>) {
                PopularResponses.observeForever { response ->
                    val popularList = ArrayList<PopularEntity>()
                    response?.forEach {
                        val popular = PopularEntity(
                            it.id,
                            it.overview,
                            it.posterPath,
                            it.backdropPath,
                            it.name,
                            it.title,
                            it.voteAverage,
                            it.language
                        )
                        popularList.add(popular)
                    }
                    popularResult.postValue(popularList)
                }
            }

        })
        return popularResult
    }
}

