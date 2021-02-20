package com.kotlin.andi.cinema.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviesFav(
    val id: Int = 0,
    val movieId: String? = null,
    val overview: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val name: String? = null,
    val title: String? = null,
    val voteAverage: Float? = null,
    val language: String? = null
) : Parcelable
