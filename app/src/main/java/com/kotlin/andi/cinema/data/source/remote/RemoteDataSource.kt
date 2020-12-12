package com.kotlin.andi.cinema.data.source.remote

import androidx.lifecycle.LiveData
import com.kotlin.andi.cinema.data.source.remote.response.ResultsMovies
import com.kotlin.andi.cinema.data.source.remote.response.ResultsPopular
import com.kotlin.andi.cinema.data.source.remote.response.ResultsTV
import com.kotlin.andi.cinema.utils.EspressoIdlingResource
import com.kotlin.andi.cinema.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper)
            }
    }

    fun getMovies(callback: LoadMovieCallback) {
        EspressoIdlingResource.increment()
        callback.onAllMoviesReceived(
            jsonHelper.loadCinema()
        )
        EspressoIdlingResource.decrement()
    }

    interface LoadMovieCallback {
        fun onAllMoviesReceived(movieResponses: LiveData<List<ResultsMovies>>)
    }

    fun getTVShows(callback: LoadTVCallback) {
        EspressoIdlingResource.increment()
        callback.onAllTVShowsReceived(
            jsonHelper.loadTVShows()
        )
        EspressoIdlingResource.decrement()
    }

    interface LoadTVCallback {
        fun onAllTVShowsReceived(tvResponses: LiveData<List<ResultsTV>>)
    }

    fun getPopular(callback: LoadPopularCallback) {
        EspressoIdlingResource.increment()
        callback.onAllPopularReceived(
            jsonHelper.loadPopular()
        )
        EspressoIdlingResource.decrement()
    }

    interface LoadPopularCallback {
        fun onAllPopularReceived(popularResponses: LiveData<List<ResultsPopular>>)
    }
}
