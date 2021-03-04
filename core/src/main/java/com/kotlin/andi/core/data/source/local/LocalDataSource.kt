package com.kotlin.andi.core.data.source.local

import kotlinx.coroutines.flow.Flow
import androidx.paging.DataSource
import com.kotlin.andi.core.data.source.local.entity.MoviesEntity
import com.kotlin.andi.core.data.source.local.entity.TVEntity
import com.kotlin.andi.core.data.source.local.entity.favorite.MoviesFavEntity
import com.kotlin.andi.core.data.source.local.entity.favorite.TVFavEntity
import com.kotlin.andi.core.data.source.local.room.MovieDao

class LocalDataSource(private val mMovieDao: MovieDao) {

    // Insert All Movies Data
    suspend fun addMovies(moviesEntity: List<MoviesEntity>) = mMovieDao.addMovies(moviesEntity)
    suspend fun addTVShows(tvEntity: List<TVEntity>) = mMovieDao.addTVShows(tvEntity)

    // Read Movies
    fun getAllMovie(): DataSource.Factory<Int, MoviesEntity> = mMovieDao.readAllMovie()
    fun getAllTVShows(): DataSource.Factory<Int, TVEntity> = mMovieDao.readAllTVShows()
    // Favorite
    // Check Movies
    fun checkFavMovies(movieId: String): Flow<List<MoviesFavEntity>> = mMovieDao.checkFavMovies(movieId)
    fun checkFavTV(tvId: String): Flow<List<TVFavEntity>> = mMovieDao.checkFavTV(tvId)

    // Insert Movies
    suspend fun addFavMovies(moviesFavEntity: MoviesFavEntity) = mMovieDao.addFavMovies(moviesFavEntity)
    suspend fun addFavTV(tvFavEntity: TVFavEntity) = mMovieDao.addFavTVShows(tvFavEntity)

    // Read Movies
    fun readFavMovies(): DataSource.Factory<Int, MoviesFavEntity> = mMovieDao.readFavMovies()
    fun readFavTV(): DataSource.Factory<Int, TVFavEntity> = mMovieDao.readFavTV()

    // Delete Movies
    suspend fun deleteMovies(moviesFavEntity: MoviesFavEntity) = mMovieDao.deleteMovies(moviesFavEntity)
    suspend fun deleteTVShows(tvFavEntity: TVFavEntity) = mMovieDao.deleteTVShows(tvFavEntity)
}
