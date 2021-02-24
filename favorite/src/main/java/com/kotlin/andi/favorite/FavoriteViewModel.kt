package com.kotlin.andi.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kotlin.andi.core.domain.model.MoviesFav
import com.kotlin.andi.core.domain.model.TVFav
import com.kotlin.andi.core.domain.usecase.MoviesUseCase

class FavoriteViewModel(private val moviesUseCase: MoviesUseCase) : ViewModel() {
    // Movies
    fun addMoviesFav(moviesFav: MoviesFav) = moviesUseCase.addMoviesFav(moviesFav)
    val readFavMovies = moviesUseCase.readFavMovies().asLiveData()
    fun deleteMoviesFav(moviesFav: MoviesFav) = moviesUseCase.deleteMoviesFav(moviesFav)
    // TV
    fun addTVFav(tvFav: TVFav) = moviesUseCase.addTVFav(tvFav)
    val readFavTV = moviesUseCase.readFavTV().asLiveData()
    fun deleteTVFav(tvFav: TVFav) = moviesUseCase.deleteTVFav(tvFav)
}
