package com.kotlin.andi.cinema.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.kotlin.andi.cinema.domain.model.Movies
import com.kotlin.andi.cinema.domain.model.TV
import com.kotlin.andi.cinema.domain.usecase.MoviesUseCase
import com.kotlin.andi.cinema.vo.Resource

class HomeViewModel(private val moviesUseCase: MoviesUseCase) : ViewModel() {
    fun getAllMovies(): LiveData<Resource<PagedList<Movies>>> = moviesUseCase.getAllMovies()
    fun getAllTVShows(): LiveData<Resource<PagedList<TV>>> = moviesUseCase.getAllTVShows()
}
