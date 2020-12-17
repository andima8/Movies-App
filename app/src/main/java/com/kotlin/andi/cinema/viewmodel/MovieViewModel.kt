package com.kotlin.andi.cinema.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kotlin.andi.cinema.data.source.local.entity.MoviesEntity
import com.kotlin.andi.cinema.data.source.local.entity.PopularEntity
import com.kotlin.andi.cinema.data.source.local.entity.TVEntity
import com.kotlin.andi.cinema.data.MovieRepository
import com.kotlin.andi.cinema.data.source.local.entity.favorite.MoviesFavEntity
import com.kotlin.andi.cinema.data.source.local.entity.favorite.TVFavEntity
import com.kotlin.andi.cinema.vo.Resource

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getAllMovies(): LiveData<Resource<List<MoviesEntity>>> = movieRepository.getAllMovies()
    fun getAllTVShows(): LiveData<Resource<List<TVEntity>>> = movieRepository.getAllTVShows()
    fun getPopular(): LiveData<Resource<List<PopularEntity>>> = movieRepository.getAllPopular()
    // Movies
    fun addMoviesFav(moviesFavEntity: MoviesFavEntity) = movieRepository.addFavMovies(moviesFavEntity)
    fun readFavMovies(): LiveData<List<MoviesFavEntity>> = movieRepository.readFavMovies()
    fun checkFavMovies(movieId: String): LiveData<List<MoviesFavEntity>> = movieRepository.checkFavMovies(movieId)
    fun deleteMoviesFav(moviesFavEntity: MoviesFavEntity) = movieRepository.deleteFavMovies(moviesFavEntity)
    // TV
    fun addTVFav(tvFavEntity: TVFavEntity) = movieRepository.addFavTV(tvFavEntity)
    fun readFavTV(): LiveData<List<TVFavEntity>> = movieRepository.readFavTV()
    fun checkFavTV(tvId: String): LiveData<List<TVFavEntity>> = movieRepository.checkFavTV(tvId)
    fun deleteTVFav(tvFavEntity: TVFavEntity) = movieRepository.deleteTVMovies(tvFavEntity)
}
