package com.kotlin.andi.cinema.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kotlin.andi.cinema.data.source.remote.RemoteDataSource
import com.kotlin.andi.cinema.movies.data.FakeMovieRepository
import com.kotlin.andi.cinema.movies.utils.LiveDataTestUtil
import com.kotlin.andi.cinema.utils.DataDummy
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class MovieRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val movieRepository = FakeMovieRepository(remote)

    private val movieResponses = DataDummy.generateDummyMovies()
    private val tvResponse = DataDummy.generateDummyTV()
    private val popularResponse = DataDummy.generateDummyPopular()

    @Test
    fun testGetAllMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMovieCallback)
                .onAllMoviesReceived(movieResponses)
            null
        }.`when`(remote).getMovies(any())
        val movieEntities = LiveDataTestUtil.getValue(movieRepository.getAllMovies())
        verify(remote).getMovies(any())
        assertNotNull(movieEntities)
        assertEquals(movieResponses.value?.size?.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun testGetAllTVShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTVCallback)
                .onAllTVShowsReceived(tvResponse)
            null
        }.`when`(remote).getTVShows(any())
        val tvEntities = LiveDataTestUtil.getValue(movieRepository.getAllTVShows())
        verify(remote).getTVShows(any())
        assertNotNull(tvEntities)
        assertEquals(tvResponse.value?.size, tvEntities.size)
    }

    @Test
    fun testGetAllPopular() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadPopularCallback)
                .onAllPopularReceived(popularResponse)
            null
        }.`when`(remote).getPopular(any())
        val popularEntities = LiveDataTestUtil.getValue(movieRepository.getAllPopular())
        verify(remote).getPopular(any())
        assertNotNull(popularEntities)
        assertEquals(popularResponse.value?.size, popularEntities.size)
    }
}
