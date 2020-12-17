package com.kotlin.andi.cinema.data

import androidx.lifecycle.LiveData
import com.kotlin.andi.cinema.data.source.local.entity.MoviesEntity
import com.kotlin.andi.cinema.data.source.local.entity.PopularEntity
import com.kotlin.andi.cinema.data.source.local.entity.TVEntity
import com.kotlin.andi.cinema.data.source.local.entity.favorite.MoviesFavEntity
import com.kotlin.andi.cinema.data.source.local.entity.favorite.TVFavEntity
import com.kotlin.andi.cinema.vo.Resource

interface MovieDataSource {
    fun getAllMovies(): LiveData<Resource<List<MoviesEntity>>>
    fun getAllTVShows(): LiveData<Resource<List<TVEntity>>>
    fun getAllPopular(): LiveData<Resource<List<PopularEntity>>>
    fun addFavMovies(moviesFavEntity: MoviesFavEntity)
    fun addFavTV(tvFavEntity: TVFavEntity)
    fun readFavMovies(): LiveData<List<MoviesFavEntity>>
    fun readFavTV(): LiveData<List<TVFavEntity>>
    fun checkFavMovies(movieId: String): LiveData<List<MoviesFavEntity>>
    fun checkFavTV(tvId: String): LiveData<List<TVFavEntity>>
    fun deleteFavMovies(moviesFavEntity: MoviesFavEntity)
    fun deleteTVMovies(tvFavEntity: TVFavEntity)
}
