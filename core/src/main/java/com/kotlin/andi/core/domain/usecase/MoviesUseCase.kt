package com.kotlin.andi.core.domain.usecase

import androidx.paging.PagedList
import com.kotlin.andi.core.domain.model.Movies
import com.kotlin.andi.core.domain.model.MoviesFav
import com.kotlin.andi.core.domain.model.TV
import com.kotlin.andi.core.domain.model.TVFav
import com.kotlin.andi.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface MoviesUseCase {
    fun getAllMovies(): Flow<Resource<PagedList<Movies>>>
    fun getAllTVShows(): Flow<Resource<PagedList<TV>>>
    // Add Favorite
    fun addMoviesFav(moviesFav: MoviesFav)
    fun addTVFav(tvFav: TVFav)
    // Show Favorite
    fun readFavMovies(): Flow<PagedList<MoviesFav>>
    fun readFavTV(): Flow<PagedList<TVFav>>
    // Check Favorite
    fun checkFavMovies(movieId: String): Flow<List<MoviesFav>>
    fun checkFavTV(tvId: String): Flow<List<TVFav>>
    // Delete Favorite
    fun deleteMoviesFav(moviesFav: MoviesFav)
    fun deleteTVFav(tvFav: TVFav)
}
