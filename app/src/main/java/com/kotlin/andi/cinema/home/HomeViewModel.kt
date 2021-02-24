package com.kotlin.andi.cinema.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kotlin.andi.core.domain.usecase.MoviesUseCase

class HomeViewModel(moviesUseCase: MoviesUseCase) : ViewModel() {
    val getAllMovies = moviesUseCase.getAllMovies().asLiveData()
    val getAllTVShows = moviesUseCase.getAllTVShows().asLiveData()
}
