package com.kotlin.andi.cinema.core.data.source.local.entity.favorite

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tv_fav_table")
@Parcelize
data class TVFavEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val tvId: String? = null,
    val overview: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val name: String? = null,
    val voteAverage: Float? = null,
    val language: String? = null
) : Parcelable
