package com.kotlin.andi.cinema.movies.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.kotlin.andi.cinema.data.source.local.entity.MoviesEntity
import com.kotlin.andi.cinema.data.source.local.entity.PopularEntity
import com.kotlin.andi.cinema.data.source.local.entity.TVEntity
import com.kotlin.andi.cinema.data.MovieRepository
import com.kotlin.andi.cinema.utils.DataDummy
import com.kotlin.andi.cinema.viewmodel.MovieViewModel
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observerCinema: Observer<List<MoviesEntity>>

    @Mock
    private lateinit var observerTV: Observer<List<TVEntity>>

    @Mock
    private lateinit var observerPopular: Observer<List<PopularEntity>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = DataDummy.generateDummyMovies()
        val movies = MutableLiveData<List<MoviesEntity>>()
        dummyMovies.observeForever { response ->
            val movieData = ArrayList<MoviesEntity>()
            response.forEach {
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
                movieData.add(movie)
            }
            movies.value = movieData
        }
        Mockito.`when`(movieRepository.getAllMovies()).thenReturn(movies)
        val moviesEntity = viewModel.getAllMovies().value
        verify(movieRepository).getAllMovies()
        Assert.assertNotNull(moviesEntity)
        assertEquals(2, moviesEntity?.size)

        viewModel.getAllMovies().observeForever(observerCinema)
        verify(observerCinema).onChanged(moviesEntity)
    }

    @Test
    fun getAllTVShows() {
        val dummyTV = DataDummy.generateDummyTV()
        val tv = MutableLiveData<List<TVEntity>>()
        dummyTV.observeForever { response ->
            val movieData = ArrayList<TVEntity>()
            response.forEach {
                val movie = TVEntity(
                    it.id,
                    it.overview,
                    it.posterPath,
                    it.backdropPath,
                    it.name,
                    it.voteAverage,
                    it.language
                )
                movieData.add(movie)
            }
            tv.value = movieData
        }

        Mockito.`when`(movieRepository.getAllTVShows()).thenReturn(tv)
        val tvEntity = viewModel.getAllTVShows().value
        verify(movieRepository).getAllTVShows()
        Assert.assertNotNull(tvEntity)
        assertEquals(2, tvEntity?.size)

        viewModel.getAllTVShows().observeForever(observerTV)
        verify(observerTV).onChanged(tvEntity)
    }

    @Test
    fun getPopular() {
        val dummyPopular = DataDummy.generateDummyPopular()
        val popular = MutableLiveData<List<PopularEntity>>()
        dummyPopular.observeForever { response ->
            val movieData = ArrayList<PopularEntity>()
            response.forEach {
                val movie = PopularEntity(
                    it.id,
                    it.overview,
                    it.posterPath,
                    it.backdropPath,
                    it.name,
                    it.title,
                    it.voteAverage,
                    it.language
                )
                movieData.add(movie)
            }
            popular.value = movieData
        }

        Mockito.`when`(movieRepository.getAllPopular()).thenReturn(popular)
        val popularEntity = viewModel.getPopular().value
        verify(movieRepository).getAllPopular()
        Assert.assertNotNull(popularEntity)
        assertEquals(2, popularEntity?.size)

        viewModel.getPopular().observeForever(observerPopular)
        verify(observerPopular).onChanged(popularEntity)
    }
}
