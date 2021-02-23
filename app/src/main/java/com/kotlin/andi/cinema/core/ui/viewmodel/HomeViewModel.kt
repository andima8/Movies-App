package com.kotlin.andi.cinema.core.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kotlin.andi.cinema.core.domain.usecase.MoviesUseCase


class HomeViewModel(private val moviesUseCase: MoviesUseCase) : ViewModel() {
    val getAllMovies = moviesUseCase.getAllMovies().asLiveData()
    val getAllTVShows = moviesUseCase.getAllTVShows().asLiveData()
}
