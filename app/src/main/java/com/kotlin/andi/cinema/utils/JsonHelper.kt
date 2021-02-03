package com.kotlin.andi.cinema.utils

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.kotlin.andi.cinema.BuildConfig
import com.kotlin.andi.cinema.api.ApiConfig
import com.kotlin.andi.cinema.data.source.remote.response.ResultsMovies
import com.kotlin.andi.cinema.data.source.remote.response.ResultsTV
import com.kotlin.andi.cinema.data.source.remote.response.MovieResponse
import com.kotlin.andi.cinema.data.source.remote.response.TVResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JsonHelper {

    private val movies = MutableLiveData<List<ResultsMovies>>()
    private val tv = MutableLiveData<List<ResultsTV>>()

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
                    Log.e(TAG, "onFailure: " + response.message())
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: " + t.message.toString())
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
                Log.e(TAG, "onFailure: " + t.message.toString())
            }
        })
        return tv
    }
}
