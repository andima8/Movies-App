package com.kotlin.andi.core.data.source.remote

import android.util.Log
import com.kotlin.andi.core.BuildConfig
import com.kotlin.andi.core.data.source.remote.api.ApiService
import com.kotlin.andi.core.data.source.remote.response.ResultsMovies
import com.kotlin.andi.core.data.source.remote.response.ResultsTV
import com.kotlin.andi.core.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

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
