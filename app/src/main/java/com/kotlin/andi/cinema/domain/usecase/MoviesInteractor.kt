package com.kotlin.andi.cinema.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.kotlin.andi.cinema.data.MovieRepository
import com.kotlin.andi.cinema.domain.model.Movies
import com.kotlin.andi.cinema.domain.model.MoviesFav
import com.kotlin.andi.cinema.domain.model.TV
import com.kotlin.andi.cinema.domain.model.TVFav
import com.kotlin.andi.cinema.domain.repository.IMoviesRepository
import com.kotlin.andi.cinema.vo.Resource

class MoviesInteractor(private val iMoviesRepository: IMoviesRepository) : MoviesUseCase {
    override fun getAllMovies(): LiveData<Resource<PagedList<Movies>>> = iMoviesRepository.getAllMovies()
    override fun getAllTVShows(): LiveData<Resource<PagedList<TV>>> = iMoviesRepository.getAllTVShows()
    // Movies
    override fun addMoviesFav(moviesFav: MoviesFav) = iMoviesRepository.addFavMovies(moviesFav)
    override fun readFavMovies(): LiveData<PagedList<MoviesFav>> = iMoviesRepository.readFavMovies()
    override fun checkFavMovies(movieId: String): LiveData<List<MoviesFav>> = iMoviesRepository.checkFavMovies(movieId)
    override fun deleteMoviesFav(moviesFav: MoviesFav) = iMoviesRepository.deleteFavMovies(moviesFav)
    // TV
    override fun addTVFav(tvFav: TVFav) = iMoviesRepository.addFavTV(tvFav)
    override fun readFavTV(): LiveData<PagedList<TVFav>> = iMoviesRepository.readFavTV()
    override fun checkFavTV(tvId: String): LiveData<List<TVFav>> = iMoviesRepository.checkFavTV(tvId)
    override fun deleteTVFav(tvFav: TVFav) = iMoviesRepository.deleteTVMovies(tvFav)
}
