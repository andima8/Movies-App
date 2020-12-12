package com.kotlin.andi.cinema.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TVEntity(
    val id: Int? = 0,
    val overview: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val name: String? = null,
    val voteAverage: Float? = null,
    val language: String? = null
) : Parcelable
