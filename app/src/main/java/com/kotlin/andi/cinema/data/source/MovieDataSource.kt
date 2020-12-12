package com.kotlin.andi.cinema.data.source

import androidx.lifecycle.LiveData
import com.kotlin.andi.cinema.data.MoviesEntity
import com.kotlin.andi.cinema.data.PopularEntity
import com.kotlin.andi.cinema.data.TVEntity

interface MovieDataSource {
    fun getAllMovies(): LiveData<List<MoviesEntity>>
    fun getAllTVShows(): LiveData<List<TVEntity>>
    fun getAllPopular(): LiveData<List<PopularEntity>>
}
