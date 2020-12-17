package com.kotlin.andi.cinema.data.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "popular_table")
@Parcelize
data class PopularEntity(
    @PrimaryKey
    val id: Int? = 0,
    val overview: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val name: String? = null,
    val title: String? = null,
    val voteAverage: Float? = 0f,
    val language: String? = null,
    val mediaType: String? = null
) : Parcelable
