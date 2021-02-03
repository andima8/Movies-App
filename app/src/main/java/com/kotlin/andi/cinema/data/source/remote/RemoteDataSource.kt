package com.kotlin.andi.cinema.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kotlin.andi.cinema.data.source.remote.response.ResultsMovies
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

    fun getMovies(): LiveData<ApiResponse<MutableLiveData<List<ResultsMovies>>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<MutableLiveData<List<ResultsMovies>>>>()
        result.value = ApiResponse.success(jsonHelper.loadCinema())
        EspressoIdlingResource.decrement()
        return result
    }

    fun getTVShows(): LiveData<ApiResponse<MutableLiveData<List<ResultsTV>>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<MutableLiveData<List<ResultsTV>>>>()
        result.value = ApiResponse.success(jsonHelper.loadTVShows())
        EspressoIdlingResource.decrement()
        return result
    }
}
