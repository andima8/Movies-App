package com.kotlin.andi.cinema.utils

import com.kotlin.andi.cinema.data.source.local.entity.MoviesEntity
import com.kotlin.andi.cinema.data.source.local.entity.TVEntity
import com.kotlin.andi.cinema.data.source.local.entity.favorite.MoviesFavEntity
import com.kotlin.andi.cinema.data.source.local.entity.favorite.TVFavEntity
import com.kotlin.andi.cinema.data.source.remote.response.ResultsMovies
import com.kotlin.andi.cinema.data.source.remote.response.ResultsTV
import com.kotlin.andi.cinema.domain.model.Movies
import com.kotlin.andi.cinema.domain.model.MoviesFav
import com.kotlin.andi.cinema.domain.model.TV
import com.kotlin.andi.cinema.domain.model.TVFav

object DataMapper {

    //MOVIE
    fun mapResponseToMovieEntities(input: List<ResultsMovies>): List<MoviesEntity> {
        val movieList = ArrayList<MoviesEntity>()
        input.map {
            val movie = MoviesEntity(
                it.id,
                it.overview,
                it.posterPath,
                it.backdropPath,
                it.name,
                it.title,
                it.voteAverage,
                it.language
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToMoviesDomain(input: MoviesEntity) = Movies(
        id = input.id,
        overview = input.overview,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        name = input.name,
        title = input.title,
        voteAverage = input.voteAverage,
        language = input.language
    )

    //TV
    fun mapResponseToTVEntities(input: List<ResultsTV>): List<TVEntity> {
        val tvList = ArrayList<TVEntity>()
        input.map {
            val tv = TVEntity(
                it.id,
                it.overview,
                it.posterPath,
                it.backdropPath,
                it.name,
                it.voteAverage,
                it.language
            )
            tvList.add(tv)
        }
        return tvList
    }

    fun mapEntitiesToTVDomain(input: TVEntity) = TV(
        id = input.id,
        overview = input.overview,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        name = input.name,
        voteAverage = input.voteAverage,
        language = input.language
    )

    //Favorite
    fun mapDomainToMoviesFavEntities(input: MoviesFav) = MoviesFavEntity(
        id = input.id,
        movieId = input.movieId,
        overview = input.overview,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        name = input.name,
        title = input.title,
        voteAverage = input.voteAverage,
        language = input.language
    )

    fun mapEntitiesToMoviesFavDomain(input: MoviesFavEntity) = MoviesFav(
        id = input.id,
        movieId = input.movieId,
        overview = input.overview,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        name = input.name,
        title = input.title,
        voteAverage = input.voteAverage,
        language = input.language
    )

    fun mapDomainToTVFavEntities(input: TVFav) = TVFavEntity(
        id = input.id,
        tvId = input.tvId,
        overview = input.overview,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        name = input.name,
        voteAverage = input.voteAverage,
        language = input.language
    )

    fun mapEntitiesToTVFavDomain(input: TVFavEntity) = TVFav(
        id = input.id,
        overview = input.overview,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        name = input.name,
        voteAverage = input.voteAverage,
        language = input.language
    )

    //Check

    fun mapCheckMovies(input: List<MoviesFavEntity>): List<MoviesFav> =
        input.map {
            MoviesFav(
                id = it.id,
                movieId = it.movieId,
                overview = it.overview,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                name = it.name,
                title = it.title,
                voteAverage = it.voteAverage,
                language = it.language
            )
        }

    fun mapCheckTV(input: List<TVFavEntity>): List<TVFav> =
        input.map {
            TVFav(
                id = it.id,
                tvId = it.tvId,
                overview = it.overview,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                name = it.name,
                voteAverage = it.voteAverage,
                language = it.language
            )
        }
}
