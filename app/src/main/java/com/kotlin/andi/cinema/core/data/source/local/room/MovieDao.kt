package com.kotlin.andi.cinema.core.data.source.local.room

import kotlinx.coroutines.flow.Flow
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Query
import androidx.room.OnConflictStrategy
import com.kotlin.andi.cinema.core.data.source.local.entity.MoviesEntity
import com.kotlin.andi.cinema.core.data.source.local.entity.TVEntity
import com.kotlin.andi.cinema.core.data.source.local.entity.favorite.MoviesFavEntity
import com.kotlin.andi.cinema.core.data.source.local.entity.favorite.TVFavEntity

@Dao
interface MovieDao {

    // Insert to DB
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(moviesEntity: List<MoviesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTVShows(tvEntity: List<TVEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavMovies(moviesFavEntity: MoviesFavEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavTVShows(tvFavEntity: TVFavEntity)

    // Delete
    @Delete
    suspend fun deleteMovies(moviesFavEntity: MoviesFavEntity)

    @Delete
    suspend fun deleteTVShows(tvFavEntity: TVFavEntity)

    // Check Favorite
    @Query("SELECT * FROM movie_fav_table WHERE movieId =:movieId")
    fun checkFavMovies(movieId: String): Flow<List<MoviesFavEntity>>

    @Query("SELECT * FROM tv_fav_table WHERE tvId =:tvId")
    fun checkFavTV(tvId: String): Flow<List<TVFavEntity>>

    // Select Data
    @Query("SELECT * FROM movie_table")
    fun readAllMovie(): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM tv_table")
    fun readAllTVShows(): DataSource.Factory<Int, TVEntity>

    // Select Favorite
    @Query("SELECT * FROM movie_fav_table ORDER BY id")
    fun readFavMovies(): DataSource.Factory<Int, MoviesFavEntity>

    @Query("SELECT * FROM tv_fav_table ORDER BY id")
    fun readFavTV(): DataSource.Factory<Int, TVFavEntity>
}
