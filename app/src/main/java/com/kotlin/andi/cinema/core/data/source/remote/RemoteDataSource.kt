package com.kotlin.andi.cinema.core.data.source.remote

import android.util.Log
import com.kotlin.andi.cinema.BuildConfig
import com.kotlin.andi.cinema.core.api.ApiService
import com.kotlin.andi.cinema.core.data.source.remote.response.ResultsMovies
import com.kotlin.andi.cinema.core.data.source.remote.response.ResultsTV
import com.kotlin.andi.cinema.core.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiService: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(apiService)
            }
    }

    suspend fun getMovies(): Flow<ApiResponse<List<ResultsMovies>>> {
        return flow {
            EspressoIdlingResource.increment()
            try {
                val response = apiService.getNowPlayingMovies(BuildConfig.API_KEY)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("Remote Data Source M", e.toString())
            }
            EspressoIdlingResource.decrement()
        }.flowOn(Dispatchers.IO)
    }

    fun getTVShows(): Flow<ApiResponse<List<ResultsTV>>> {
        return flow {
            EspressoIdlingResource.increment()
            try {
                val response = apiService.getTvShows(BuildConfig.API_KEY)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.d("Remote Data Source TV", e.toString())
            }
            EspressoIdlingResource.decrement()
        }.flowOn(Dispatchers.IO)
    }
}
