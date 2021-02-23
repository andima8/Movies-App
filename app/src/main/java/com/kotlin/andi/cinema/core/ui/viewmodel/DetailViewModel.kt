package com.kotlin.andi.cinema.core.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.kotlin.andi.cinema.core.data.MovieRepository
import com.kotlin.andi.cinema.core.domain.model.Movies
import com.kotlin.andi.cinema.core.domain.model.MoviesFav
import com.kotlin.andi.cinema.core.domain.model.TV
import com.kotlin.andi.cinema.core.domain.model.TVFav
import com.kotlin.andi.cinema.core.domain.usecase.MoviesUseCase
import com.kotlin.andi.cinema.core.vo.Resource
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
