package com.kotlin.andi.cinema.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.kotlin.andi.cinema.data.MovieRepository
import com.kotlin.andi.cinema.domain.model.Movies
import com.kotlin.andi.cinema.domain.model.MoviesFav
import com.kotlin.andi.cinema.domain.model.TV
import com.kotlin.andi.cinema.domain.model.TVFav
import com.kotlin.andi.cinema.domain.usecase.MoviesUseCase
import com.kotlin.andi.cinema.vo.Resource

class DetailViewModel(private val moviesUseCase: MoviesUseCase) : ViewModel() {
    // Movies
    fun addMoviesFav(moviesFav: MoviesFav) = moviesUseCase.addMoviesFav(moviesFav)
    fun checkFavMovies(movieId: String): LiveData<List<MoviesFav>> = moviesUseCase.checkFavMovies(movieId)
    fun deleteMoviesFav(moviesFav: MoviesFav) = moviesUseCase.deleteMoviesFav(moviesFav)
    // TV
    fun addTVFav(tvFav: TVFav) = moviesUseCase.addTVFav(tvFav)
    fun checkFavTV(tvId: String): LiveData<List<TVFav>> = moviesUseCase.checkFavTV(tvId)
    fun deleteTVFav(tvFav: TVFav) = moviesUseCase.deleteTVFav(tvFav)
}
