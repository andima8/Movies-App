package com.kotlin.andi.cinema.viewmodel

import androidx.lifecycle.ViewModel
import com.kotlin.andi.cinema.data.MovieEntity
import com.kotlin.andi.cinema.utils.DataDummy

class MovieViewModel: ViewModel() {

    companion object {
        const val CINEMA = "Cinema"
        const val TV_SHOWS = "TV Shows"
    }

    fun getMovies(): MutableList<MovieEntity> = DataDummy.generateDummyMovies()
    fun getCinema(): List<MovieEntity> = DataDummy.generateDummyMovies().filter { it.typeMovie == CINEMA }
    fun getTVShows() : List<MovieEntity> = DataDummy.generateDummyMovies().filter { it.typeMovie == TV_SHOWS }
}
