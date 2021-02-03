package com.kotlin.andi.cinema.data.source.local.entity.favorite

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "movie_fav_table")
@Parcelize
data class MoviesFavEntity(
    @PrimaryKey(autoGenerate = true)
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
