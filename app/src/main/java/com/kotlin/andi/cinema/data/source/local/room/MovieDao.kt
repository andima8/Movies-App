package com.kotlin.andi.cinema.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kotlin.andi.cinema.data.source.local.entity.MoviesEntity
import com.kotlin.andi.cinema.data.source.local.entity.PopularEntity
import com.kotlin.andi.cinema.data.source.local.entity.TVEntity
import com.kotlin.andi.cinema.data.source.local.entity.favorite.MoviesFavEntity
import com.kotlin.andi.cinema.data.source.local.entity.favorite.TVFavEntity

@Dao
interface MovieDao {

    // Insert to DB
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovies(moviesEntity: List<MoviesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTVShows(tvEntity: List<TVEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPopular(popularEntity: List<PopularEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavMovies(moviesFavEntity: MoviesFavEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavTVShows(tvFavEntity: TVFavEntity)

    // Delete
    @Delete
    fun deleteMovies(moviesFavEntity: MoviesFavEntity)

    @Delete
    fun deleteTVShows(tvFavEntity: TVFavEntity)

    // Check Favorite
    @Query("SELECT * FROM movie_fav_table WHERE movieId =:movieId")
    fun checkFavMovies(movieId: String): LiveData<List<MoviesFavEntity>>

    @Query("SELECT * FROM tv_fav_table WHERE tvId =:tvId")
    fun checkFavTV(tvId: String): LiveData<List<TVFavEntity>>

    // Select Data
    @Query("SELECT * FROM movie_table")
    fun readAllMovie(): LiveData<List<MoviesEntity>>

    @Query("SELECT * FROM tv_table")
    fun readAllTVShows(): LiveData<List<TVEntity>>

    @Query("SELECT * FROM popular_table")
    fun readAllPopular(): LiveData<List<PopularEntity>>

    // Select Favorite
    @Query("SELECT * FROM movie_fav_table ORDER BY id")
    fun readFavMovies(): LiveData<List<MoviesFavEntity>>

    @Query("SELECT * FROM tv_fav_table ORDER BY id")
    fun readFavTV(): LiveData<List<TVFavEntity>>

}
