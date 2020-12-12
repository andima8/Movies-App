package com.kotlin.andi.cinema.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TVResponse(
    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("results")
    val results: List<ResultsTV>,

    @field:SerializedName("total_results")
    val totalResults: Int
) : Parcelable

@Parcelize
data class ResultsTV(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("backdrop_path")
    val backdropPath: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("vote_average")
    val voteAverage: Float? = 0f,

    @field:SerializedName("original_language")
    val language: String? = null
) : Parcelable
