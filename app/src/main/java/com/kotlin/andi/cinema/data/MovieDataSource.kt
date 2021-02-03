package com.kotlin.andi.cinema.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.kotlin.andi.cinema.data.source.local.entity.MoviesEntity
import com.kotlin.andi.cinema.data.source.local.entity.TVEntity
import com.kotlin.andi.cinema.data.source.local.entity.favorite.MoviesFavEntity
import com.kotlin.andi.cinema.data.source.local.entity.favorite.TVFavEntity
import com.kotlin.andi.cinema.vo.Resource

interface MovieDataSource {
    fun getAllMovies(): LiveData<Resource<PagedList<MoviesEntity>>>
    fun getAllTVShows(): LiveData<Resource<PagedList<TVEntity>>>
    // Add Favorite
    fun addFavMovies(moviesFavEntity: MoviesFavEntity)
    fun addFavTV(tvFavEntity: TVFavEntity)
    // Show Favorite
    fun readFavMovies(): LiveData<PagedList<MoviesFavEntity>>
    fun readFavTV(): LiveData<PagedList<TVFavEntity>>
    // Check Favorite
    fun checkFavMovies(movieId: String): LiveData<List<MoviesFavEntity>>
    fun checkFavTV(tvId: String): LiveData<List<TVFavEntity>>
    // Delete Favorite
    fun deleteFavMovies(moviesFavEntity: MoviesFavEntity)
    fun deleteTVMovies(tvFavEntity: TVFavEntity)
}
