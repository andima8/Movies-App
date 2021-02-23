package com.kotlin.andi.cinema.movies.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.kotlin.andi.cinema.core.data.source.local.entity.MoviesEntity
import com.kotlin.andi.cinema.core.data.source.local.entity.TVEntity
import com.kotlin.andi.cinema.core.data.MovieRepository
import com.kotlin.andi.cinema.core.data.source.local.entity.favorite.MoviesFavEntity
import com.kotlin.andi.cinema.core.data.source.local.entity.favorite.TVFavEntity
import com.kotlin.andi.cinema.core.utils.DataDummy
import com.kotlin.andi.cinema.core.ui.viewmodel.DetailViewModel
import com.kotlin.andi.cinema.core.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    companion object {
        private const val MOVIE_ID = "602211"
        private const val TV_ID = "82856"
    }

    private lateinit var viewModel: DetailViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observerCinema: Observer<Resource<PagedList<MoviesEntity>>>

    @Mock
    private lateinit var moviePagedList: PagedList<MoviesEntity>

    @Mock
    private lateinit var observerTV: Observer<Resource<PagedList<TVEntity>>>

    @Mock
    private lateinit var tvPagedList: PagedList<TVEntity>

    @Mock
    private lateinit var observerFavCinema: Observer<PagedList<MoviesFavEntity>>

    @Mock
    private lateinit var movieFavPagedList: PagedList<MoviesFavEntity>

    @Mock
    private lateinit var observerFavTV: Observer<PagedList<TVFavEntity>>

    @Mock
    private lateinit var tvFavPagedList: PagedList<TVFavEntity>

    @Mock
    private lateinit var checkObserverMovies: Observer<List<MoviesFavEntity>>

    @Mock
    private lateinit var checkObserverTV: Observer<List<TVFavEntity>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = Resource.success(moviePagedList)
        `when`(dummyMovies.data?.size).thenReturn(2)
        val movies = MutableLiveData<Resource<PagedList<MoviesEntity>>>()
        movies.value = dummyMovies

        `when`(movieRepository.getAllMovies()).thenReturn(movies)
        val moviesEntity = viewModel.getAllMovies().value?.data
        verify(movieRepository).getAllMovies()
        assertNotNull(moviesEntity)
        assertEquals(2, moviesEntity?.size)

        viewModel.getAllMovies().observeForever(observerCinema)
        verify(observerCinema).onChanged(dummyMovies)
    }

    @Test
    fun getAllTVShows() {
        val dummyTV = Resource.success(tvPagedList)
        `when`(dummyTV.data?.size).thenReturn(2)
        val tv = MutableLiveData<Resource<PagedList<TVEntity>>>()
        tv.value = dummyTV

        `when`(movieRepository.getAllTVShows()).thenReturn(tv)
        val tvEntity = viewModel.getAllTVShows().value?.data
        verify(movieRepository).getAllTVShows()
        assertNotNull(tvEntity)
        assertEquals(2, tvEntity?.size)

        viewModel.getAllTVShows().observeForever(observerTV)
        verify(observerTV).onChanged(dummyTV)
    }

    @Test
    fun getAllFavMovies() {
        val dummyMovies = movieFavPagedList
        `when`(dummyMovies.size).thenReturn(2)
        val movies = MutableLiveData<PagedList<MoviesFavEntity>>()
        movies.value = dummyMovies

        `when`(movieRepository.readFavMovies()).thenReturn(movies)
        val moviesEntity = viewModel.readFavMovies().value
        verify(movieRepository).readFavMovies()
        assertNotNull(moviesEntity)
        assertEquals(2, moviesEntity?.size)

        viewModel.readFavMovies().observeForever(observerFavCinema)
        verify(observerFavCinema).onChanged(dummyMovies)
    }

    @Test
    fun getAllFavTV() {
        val dummyMovies = tvFavPagedList
        `when`(dummyMovies.size).thenReturn(2)
        val movies = MutableLiveData<PagedList<TVFavEntity>>()
        movies.value = dummyMovies

        `when`(movieRepository.readFavTV()).thenReturn(movies)
        val moviesEntity = viewModel.readFavTV().value
        verify(movieRepository).readFavTV()
        assertNotNull(moviesEntity)
        assertEquals(2, moviesEntity?.size)

        viewModel.readFavTV().observeForever(observerFavTV)
        verify(observerFavTV).onChanged(dummyMovies)
    }

    @Test
    fun checkFavMovies() {
        val dummyMovies = DataDummy.checkDummyFavMovies(MOVIE_ID)[0]
        val movies = MutableLiveData<List<MoviesFavEntity>>()
        movies.value = listOf(dummyMovies)

        `when`(movieRepository.checkFavMovies(MOVIE_ID)).thenReturn(movies)
        val moviesFavEntity = viewModel.checkFavMovies(MOVIE_ID)
        verify(movieRepository).checkFavMovies(MOVIE_ID)
        assertNotNull(moviesFavEntity)
        assertEquals(MOVIE_ID, moviesFavEntity.value?.first()?.movieId)

        viewModel.checkFavMovies(MOVIE_ID).observeForever(checkObserverMovies)
        verify(checkObserverMovies).onChanged(listOf(dummyMovies))
    }

    @Test
    fun checkFavTV() {
        val dummyMovies = DataDummy.checkDummyFavTV(TV_ID)[0]
        val movies = MutableLiveData<List<TVFavEntity>>()
        movies.value = listOf(dummyMovies)

        `when`(movieRepository.checkFavTV(TV_ID)).thenReturn(movies)
        val tvFavEntity = viewModel.checkFavTV(TV_ID)
        verify(movieRepository).checkFavTV(TV_ID)
        assertNotNull(tvFavEntity)
        assertEquals(TV_ID, tvFavEntity.value?.first()?.tvId)

        viewModel.checkFavTV(TV_ID).observeForever(checkObserverTV)
        verify(checkObserverTV).onChanged(listOf(dummyMovies))
    }
}
