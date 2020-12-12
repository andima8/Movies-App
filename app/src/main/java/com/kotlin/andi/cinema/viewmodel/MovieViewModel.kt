package com.kotlin.andi.cinema.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kotlin.andi.cinema.data.MoviesEntity
import com.kotlin.andi.cinema.data.PopularEntity
import com.kotlin.andi.cinema.data.TVEntity
import com.kotlin.andi.cinema.data.source.MovieRepository

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getAllMovies(): LiveData<List<MoviesEntity>> = movieRepository.getAllMovies()
    fun getAllTVShows(): LiveData<List<TVEntity>> = movieRepository.getAllTVShows()
    fun getPopular(): LiveData<List<PopularEntity>> = movieRepository.getAllPopular()
}
