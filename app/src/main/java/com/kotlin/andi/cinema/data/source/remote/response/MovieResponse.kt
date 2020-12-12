package com.kotlin.andi.cinema.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse(

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("results")
    val results: List<ResultsMovies>,

    @field:SerializedName("total_results")
    val totalResults: Int
) : Parcelable

@Parcelize
data class ResultsMovies(

    @field:SerializedName("id")
    val id: Int? = 0,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Float? = 0f,

    @field:SerializedName("original_language")
    val language: String? = null
) : Parcelable
