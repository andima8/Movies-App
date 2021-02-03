package com.kotlin.andi.cinema.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.kotlin.andi.cinema.data.source.local.entity.MoviesEntity
import com.kotlin.andi.cinema.data.source.local.entity.TVEntity
import com.kotlin.andi.cinema.data.MovieRepository
import com.kotlin.andi.cinema.data.source.local.entity.favorite.MoviesFavEntity
import com.kotlin.andi.cinema.data.source.local.entity.favorite.TVFavEntity
import com.kotlin.andi.cinema.vo.Resource

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getAllMovies(): LiveData<Resource<PagedList<MoviesEntity>>> = movieRepository.getAllMovies()
    fun getAllTVShows(): LiveData<Resource<PagedList<TVEntity>>> = movieRepository.getAllTVShows()
    // Movies
    fun addMoviesFav(moviesFavEntity: MoviesFavEntity) = movieRepository.addFavMovies(moviesFavEntity)
    fun readFavMovies(): LiveData<PagedList<MoviesFavEntity>> = movieRepository.readFavMovies()
    fun checkFavMovies(movieId: String): LiveData<List<MoviesFavEntity>> = movieRepository.checkFavMovies(movieId)
    fun deleteMoviesFav(moviesFavEntity: MoviesFavEntity) = movieRepository.deleteFavMovies(moviesFavEntity)
    // TV
    fun addTVFav(tvFavEntity: TVFavEntity) = movieRepository.addFavTV(tvFavEntity)
    fun readFavTV(): LiveData<PagedList<TVFavEntity>> = movieRepository.readFavTV()
    fun checkFavTV(tvId: String): LiveData<List<TVFavEntity>> = movieRepository.checkFavTV(tvId)
    fun deleteTVFav(tvFavEntity: TVFavEntity) = movieRepository.deleteTVMovies(tvFavEntity)
}
