package com.kotlin.andi.cinema.utils

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.kotlin.andi.cinema.BuildConfig
import com.kotlin.andi.cinema.api.ApiConfig
import com.kotlin.andi.cinema.data.source.remote.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JsonHelper {

    private val movies = MutableLiveData<List<ResultsMovies>>()
    private val tv = MutableLiveData<List<ResultsTV>>()
    private val popular = MutableLiveData<List<ResultsPopular>>()

    companion object {
        private const val TAG = "JSON HELPER"
    }

    fun loadCinema(): MutableLiveData<List<ResultsMovies>> {
        val client = ApiConfig.getApiService().getNowPlayingMovies(BuildConfig.API_KEY)
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.results
                    movies.postValue(data)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return movies
    }

    fun loadTVShows(): MutableLiveData<List<ResultsTV>> {
        val client = ApiConfig.getApiService().getTvShows(BuildConfig.API_KEY)
        client.enqueue(object : Callback<TVResponse> {
            override fun onResponse(call: Call<TVResponse>, response: Response<TVResponse>) {
                val data = response.body()?.results
                tv.postValue(data)
            }

            override fun onFailure(call: Call<TVResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return tv
    }

    fun loadPopular(): MutableLiveData<List<ResultsPopular>> {
        val client = ApiConfig.getApiService().getPopular(BuildConfig.API_KEY)
        client.enqueue(object : Callback<PopularResponse> {
            override fun onResponse(
                call: Call<PopularResponse>,
                response: Response<PopularResponse>
            ) {
                val data = response.body()?.results
                popular.postValue(data)
            }

            override fun onFailure(call: Call<PopularResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return popular
    }
}
