package com.kotlin.andi.cinema.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PopularEntity(
    val id: Int? = 0,
    val overview: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val name: String? = null,
    val title: String? = null,
    val voteAverage: Float? = 0f,
    val language: String? = null
) : Parcelable
