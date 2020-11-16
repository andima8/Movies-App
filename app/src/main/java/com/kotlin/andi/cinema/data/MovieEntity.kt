package com.kotlin.andi.cinema.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieEntity(
    val movieId: String,
    val nameMovie: String,
    val bannerMovie: Int,
    val imgMovie: Int,
    val position: String,
    val detailMovie: String,
    val genreMovie: String,
    val typeMovie: String,
    val ratingMovie: Float
) : Parcelable
