package com.kotlin.andi.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tv_table")
@Parcelize
data class TVEntity(
    @PrimaryKey
    val id: Int? = 0,
    val overview: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val name: String? = null,
    val voteAverage: Float? = null,
    val language: String? = null
) : Parcelable
