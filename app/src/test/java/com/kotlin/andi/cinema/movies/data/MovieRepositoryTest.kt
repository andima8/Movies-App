package com.kotlin.andi.cinema.movies.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.kotlin.andi.cinema.core.data.source.local.LocalDataSource
import com.kotlin.andi.cinema.core.data.source.local.entity.MoviesEntity
import com.kotlin.andi.cinema.core.data.source.local.entity.TVEntity
import com.kotlin.andi.cinema.core.data.source.local.entity.favorite.MoviesFavEntity
import com.kotlin.andi.cinema.core.data.source.local.entity.favorite.TVFavEntity
import com.kotlin.andi.cinema.core.data.source.remote.RemoteDataSource
import com.kotlin.andi.cinema.movies.utils.LiveDataTestUtil
import com.kotlin.andi.cinema.movies.utils.PagedListUtil
import com.kotlin.andi.cinema.core.utils.AppExecutors
import com.kotlin.andi.cinema.core.utils.DataDummy
import com.kotlin.andi.cinema.core.vo.Resource
import org.mockito.Mockito.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`

class MovieRepositoryTest {

    companion object {
        private const val MOVIE_ID = "602211"
        private const val TV_ID = "82856"
    }

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val movieRepository = FakeMovieRepository(remote, local, appExecutors)

    private val movieResponses = DataDummy.generateDummyMovies()
    private val movieFavResponses = DataDummy.generateDummyFavMovies()
    private val tvResponse = DataDummy.generateDummyTV()
    private val tvFavResponse = DataDummy.generateDummyFavTV()
    // Favorite Check State
    private val checkFavoriteMovies = DataDummy.checkDummyFavMovies(MOVIE_ID)
    private val checkFavoriteTV = DataDummy.checkDummyFavTV(TV_ID)

    @Test
    fun testGetAllMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesEntity>
        `when`(local.getAllMovie()).thenReturn(dataSourceFactory)
        movieRepository.getAllMovies()
        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getAllMovie()
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.data.size.toLong())
    }

    @Test
    fun testGetAllTVShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVEntity>
        `when`(local.getAllTVShows()).thenReturn(dataSourceFactory)
        movieRepository.getAllTVShows()
        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTV()))
        verify(local).getAllTVShows()
        assertNotNull(movieEntities)
        assertEquals(tvResponse.size.toLong(), movieEntities.data.size.toLong())
    }

    // Favorite Testing
    @Test
    fun testGetAllMoviesFav() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesFavEntity>
        `when`(local.readFavMovies()).thenReturn(dataSourceFactory)
        movieRepository.readFavMovies()
        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyFavMovies()))
        verify(local).readFavMovies()
        assertNotNull(movieEntities)
        assertEquals(movieFavResponses.size.toLong(), movieEntities.data.size.toLong())
    }

    @Test
    fun testGetAllFavTVShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVFavEntity>
        `when`(local.readFavTV()).thenReturn(dataSourceFactory)
        movieRepository.readFavTV()
        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyFavTV()))
        verify(local).readFavTV()
        assertNotNull(movieEntities)
        assertEquals(tvFavResponse.size.toLong(), movieEntities.data.size.toLong())
    }

    @Test
    fun testCheckFavMovies() {
        val dummyMovies = MutableLiveData<List<MoviesFavEntity>>()
        dummyMovies.value = DataDummy.checkDummyFavMovies(MOVIE_ID)
        `when`(local.checkFavMovies(MOVIE_ID)).thenReturn(dummyMovies)

        val moviesFavEntity = LiveDataTestUtil.getValue(movieRepository.checkFavMovies(MOVIE_ID))
        verify(local).checkFavMovies(MOVIE_ID)
        assertNotNull(moviesFavEntity)
        assertEquals(checkFavoriteMovies.first(), moviesFavEntity.first())
    }

    @Test
    fun testCheckFavTV() {
        val dummyMovies = MutableLiveData<List<TVFavEntity>>()
        dummyMovies.value = DataDummy.checkDummyFavTV(TV_ID)
        `when`(local.checkFavTV(TV_ID)).thenReturn(dummyMovies)

        val moviesFavEntity = LiveDataTestUtil.getValue(movieRepository.checkFavTV(TV_ID))
        verify(local).checkFavTV(TV_ID)
        assertNotNull(moviesFavEntity)
        assertEquals(checkFavoriteTV.first(), moviesFavEntity.first())
    }
}
