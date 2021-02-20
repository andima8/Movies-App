package com.kotlin.andi.cinema.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.kotlin.andi.cinema.domain.model.TVFav
import com.kotlin.andi.cinema.domain.model.Movies
import com.kotlin.andi.cinema.domain.model.MoviesFav
import com.kotlin.andi.cinema.domain.model.TV
import com.kotlin.andi.cinema.vo.Resource

interface IMoviesRepository {
    fun getAllMovies(): LiveData<Resource<PagedList<Movies>>>
    fun getAllTVShows(): LiveData<Resource<PagedList<TV>>>
    // Add Favorite
    fun addFavMovies(moviesFav: MoviesFav)
    fun addFavTV(tvFav: TVFav)
    // Show Favorite
    fun readFavMovies(): LiveData<PagedList<MoviesFav>>
    fun readFavTV(): LiveData<PagedList<TVFav>>
    // Check Favorite
    fun checkFavMovies(movieId: String): LiveData<List<MoviesFav>>
    fun checkFavTV(tvId: String): LiveData<List<TVFav>>
    // Delete Favorite
    fun deleteFavMovies(moviesFav: MoviesFav)
    fun deleteTVMovies(tvFav: TVFav)
}
