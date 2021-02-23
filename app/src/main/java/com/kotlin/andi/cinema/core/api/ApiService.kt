package com.kotlin.andi.cinema.core.api

import com.kotlin.andi.cinema.core.data.source.remote.response.MovieResponse
import com.kotlin.andi.cinema.core.data.source.remote.response.TVResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/3/movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String
    ): MovieResponse

    @GET("3/tv/popular")
    suspend fun getTvShows(
        @Query("api_key") apiKey: String
    ): TVResponse
}
