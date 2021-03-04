package com.kotlin.andi.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kotlin.andi.core.data.source.local.entity.MoviesEntity
import com.kotlin.andi.core.data.source.local.entity.TVEntity
import com.kotlin.andi.core.data.source.local.entity.favorite.MoviesFavEntity
import com.kotlin.andi.core.data.source.local.entity.favorite.TVFavEntity

@Database(entities = [MoviesEntity::class, TVEntity::class, MoviesFavEntity::class, TVFavEntity::class],
    version = 1,
    exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}
