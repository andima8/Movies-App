package com.kotlin.andi.cinema.core.domain.repository

import androidx.paging.PagedList
import com.kotlin.andi.cinema.core.domain.model.TVFav
import com.kotlin.andi.cinema.core.domain.model.Movies
import com.kotlin.andi.cinema.core.domain.model.MoviesFav
import com.kotlin.andi.cinema.core.domain.model.TV
import com.kotlin.andi.cinema.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface IMoviesRepository {
    fun getAllMovies(): Flow<Resource<PagedList<Movies>>>
    fun getAllTVShows(): Flow<Resource<PagedList<TV>>>
    // Add Favorite
    fun addFavMovies(moviesFav: MoviesFav)
    fun addFavTV(tvFav: TVFav)
    // Show Favorite
    fun readFavMovies(): Flow<PagedList<MoviesFav>>
    fun readFavTV(): Flow<PagedList<TVFav>>
    // Check Favorite
    fun checkFavMovies(movieId: String): Flow<List<MoviesFav>>
    fun checkFavTV(tvId: String): Flow<List<TVFav>>
    // Delete Favorite
    fun deleteFavMovies(moviesFav: MoviesFav)
    fun deleteTVMovies(tvFav: TVFav)
}
