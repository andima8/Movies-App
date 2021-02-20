package com.kotlin.andi.cinema.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.kotlin.andi.cinema.domain.model.MoviesFav
import com.kotlin.andi.cinema.domain.model.TVFav
import com.kotlin.andi.cinema.domain.usecase.MoviesUseCase

class FavoriteViewModel(private val moviesUseCase: MoviesUseCase) : ViewModel() {
    // Movies
    fun addMoviesFav(moviesFav: MoviesFav) = moviesUseCase.addMoviesFav(moviesFav)
    fun readFavMovies(): LiveData<PagedList<MoviesFav>> = moviesUseCase.readFavMovies()
    fun deleteMoviesFav(moviesFav: MoviesFav) = moviesUseCase.deleteMoviesFav(moviesFav)
    // TV
    fun addTVFav(tvFav: TVFav) = moviesUseCase.addTVFav(tvFav)
    fun readFavTV(): LiveData<PagedList<TVFav>> = moviesUseCase.readFavTV()
    fun deleteTVFav(tvFav: TVFav) = moviesUseCase.deleteTVFav(tvFav)
}
