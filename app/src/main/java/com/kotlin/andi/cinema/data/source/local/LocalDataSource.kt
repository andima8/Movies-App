package com.kotlin.andi.cinema.data.source.local

import androidx.lifecycle.LiveData
import com.kotlin.andi.cinema.data.source.local.entity.MoviesEntity
import com.kotlin.andi.cinema.data.source.local.entity.PopularEntity
import com.kotlin.andi.cinema.data.source.local.entity.TVEntity
import com.kotlin.andi.cinema.data.source.local.entity.favorite.MoviesFavEntity
import com.kotlin.andi.cinema.data.source.local.entity.favorite.TVFavEntity
import com.kotlin.andi.cinema.data.source.local.room.MovieDao

class LocalDataSource private constructor(private val mMovieDao: MovieDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieDao)
    }

    // Insert All Movies Data
    fun addMovies(moviesEntity: List<MoviesEntity>) = mMovieDao.addMovies(moviesEntity)
    fun addTVShows(tvEntity: List<TVEntity>) = mMovieDao.addTVShows(tvEntity)
    fun addPopular(popularEntity: List<PopularEntity>) = mMovieDao.addPopular(popularEntity)

    // Read Movies
    fun getAllMovie(): LiveData<List<MoviesEntity>> = mMovieDao.readAllMovie()
    fun getAllTVShows(): LiveData<List<TVEntity>> = mMovieDao.readAllTVShows()
    fun getAllPopular(): LiveData<List<PopularEntity>> = mMovieDao.readAllPopular()

    // Favorite

    // Check Movies
    fun checkFavMovies(movieId: String): LiveData<List<MoviesFavEntity>> = mMovieDao.checkFavMovies(movieId)
    fun checkFavTV(tvId: String): LiveData<List<TVFavEntity>> = mMovieDao.checkFavTV(tvId)

    // Insert Movies
    fun addFavMovies(moviesFavEntity: MoviesFavEntity) = mMovieDao.addFavMovies(moviesFavEntity)
    fun addFavTV(tvFavEntity: TVFavEntity) = mMovieDao.addFavTVShows(tvFavEntity)

    // Read Movies
    fun readFavMovies(): LiveData<List<MoviesFavEntity>> = mMovieDao.readFavMovies()
    fun readFavTV(): LiveData<List<TVFavEntity>> = mMovieDao.readFavTV()

    //Delete Movies
    fun deleteMovies(moviesFavEntity: MoviesFavEntity) = mMovieDao.deleteMovies(moviesFavEntity)
    fun deleteTVShows(tvFavEntity: TVFavEntity) = mMovieDao.deleteTVShows(tvFavEntity)


}
