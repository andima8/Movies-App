package com.kotlin.andi.core.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.kotlin.andi.core.domain.model.MoviesFav
import com.kotlin.andi.core.domain.model.TVFav
import com.kotlin.andi.core.domain.usecase.MoviesUseCase
import kotlinx.coroutines.flow.Flow

class DetailViewModel(private val moviesUseCase: MoviesUseCase) : ViewModel() {
    // Movies
    fun addMoviesFav(moviesFav: MoviesFav) = moviesUseCase.addMoviesFav(moviesFav)
    fun checkFavMovies(movieId: String): Flow<List<MoviesFav>> = moviesUseCase.checkFavMovies(movieId)
    fun deleteMoviesFav(moviesFav: MoviesFav) = moviesUseCase.deleteMoviesFav(moviesFav)
    // TV
    fun addTVFav(tvFav: TVFav) = moviesUseCase.addTVFav(tvFav)
    fun checkFavTV(tvId: String): Flow<List<TVFav>> = moviesUseCase.checkFavTV(tvId)
    fun deleteTVFav(tvFav: TVFav) = moviesUseCase.deleteTVFav(tvFav)
}
