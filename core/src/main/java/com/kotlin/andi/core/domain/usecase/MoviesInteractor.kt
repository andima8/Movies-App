package com.kotlin.andi.core.domain.usecase

import com.kotlin.andi.core.domain.model.MoviesFav
import com.kotlin.andi.core.domain.model.TVFav
import com.kotlin.andi.core.domain.repository.IMoviesRepository

class MoviesInteractor(private val iMoviesRepository: IMoviesRepository) : MoviesUseCase {
    override fun getAllMovies() = iMoviesRepository.getAllMovies()
    override fun getAllTVShows() = iMoviesRepository.getAllTVShows()
    // Movies
    override fun addMoviesFav(moviesFav: MoviesFav) = iMoviesRepository.addFavMovies(moviesFav)
    override fun readFavMovies() = iMoviesRepository.readFavMovies()
    override fun checkFavMovies(movieId: String) = iMoviesRepository.checkFavMovies(movieId)
    override fun deleteMoviesFav(moviesFav: MoviesFav) = iMoviesRepository.deleteFavMovies(moviesFav)
    // TV
    override fun addTVFav(tvFav: TVFav) = iMoviesRepository.addFavTV(tvFav)
    override fun readFavTV() = iMoviesRepository.readFavTV()
    override fun checkFavTV(tvId: String) = iMoviesRepository.checkFavTV(tvId)
    override fun deleteTVFav(tvFav: TVFav) = iMoviesRepository.deleteTVMovies(tvFav)
}
