package com.kotlin.andi.cinema.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.kotlin.andi.cinema.domain.model.Movies
import com.kotlin.andi.cinema.domain.model.MoviesFav
import com.kotlin.andi.cinema.domain.model.TV
import com.kotlin.andi.cinema.domain.model.TVFav
import com.kotlin.andi.cinema.vo.Resource

interface MoviesUseCase {
    fun getAllMovies(): LiveData<Resource<PagedList<Movies>>>
    fun getAllTVShows(): LiveData<Resource<PagedList<TV>>>
    // Add Favorite
    fun addMoviesFav(moviesFav: MoviesFav)
    fun addTVFav(tvFav: TVFav)
    // Show Favorite
    fun readFavMovies(): LiveData<PagedList<MoviesFav>>
    fun readFavTV(): LiveData<PagedList<TVFav>>
    // Check Favorite
    fun checkFavMovies(movieId: String): LiveData<List<MoviesFav>>
    fun checkFavTV(tvId: String): LiveData<List<TVFav>>
    // Delete Favorite
    fun deleteMoviesFav(moviesFav: MoviesFav)
    fun deleteTVFav(tvFav: TVFav)
}
