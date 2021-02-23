package com.kotlin.andi.cinema.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.kotlin.andi.cinema.core.data.MovieRepository
import com.kotlin.andi.cinema.core.domain.model.Movies
import com.kotlin.andi.cinema.core.domain.model.MoviesFav
import com.kotlin.andi.cinema.core.domain.model.TV
import com.kotlin.andi.cinema.core.domain.model.TVFav
import com.kotlin.andi.cinema.core.domain.repository.IMoviesRepository
import com.kotlin.andi.cinema.core.vo.Resource

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
