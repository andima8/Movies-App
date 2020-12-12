package com.kotlin.andi.cinema.api

import com.kotlin.andi.cinema.data.source.remote.response.MovieResponse
import com.kotlin.andi.cinema.data.source.remote.response.PopularResponse
import com.kotlin.andi.cinema.data.source.remote.response.TVResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/3/trending/all/day")
    fun getPopular(
        @Query("api_key") apiKey: String
    ): Call<PopularResponse>

    @GET("/3/movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String
    ): Call<MovieResponse>

    @GET("3/tv/popular")
    fun getTvShows(
        @Query("api_key") apiKey: String
    ): Call<TVResponse>
}
