package com.kotlin.andi.cinema.core.data

import androidx.lifecycle.asFlow
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kotlin.andi.cinema.core.data.source.local.LocalDataSource
import com.kotlin.andi.cinema.core.data.source.remote.ApiResponse
import com.kotlin.andi.cinema.core.data.source.remote.RemoteDataSource
import com.kotlin.andi.cinema.core.data.source.remote.response.ResultsMovies
import com.kotlin.andi.cinema.core.data.source.remote.response.ResultsTV
import com.kotlin.andi.cinema.core.domain.model.Movies
import com.kotlin.andi.cinema.core.domain.model.MoviesFav
import com.kotlin.andi.cinema.core.domain.model.TV
import com.kotlin.andi.cinema.core.domain.model.TVFav
import com.kotlin.andi.cinema.core.domain.repository.IMoviesRepository
import com.kotlin.andi.cinema.core.utils.AppExecutors
import com.kotlin.andi.cinema.core.utils.DataMapper
import com.kotlin.andi.cinema.core.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
) : IMoviesRepository {

    companion object {
        const val SIZE = 5

        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors,
        ): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteDataSource, localData, appExecutors)
            }
    }

    override fun getAllMovies(): Flow<Resource<PagedList<Movies>>> =
        object : NetworkBoundResource<PagedList<Movies>, List<ResultsMovies>>(appExecutors) {
            override fun loadFromDB(): Flow<PagedList<Movies>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(SIZE)
                    .setPageSize(SIZE)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovie().map {
                    DataMapper.mapEntitiesToMoviesDomain(it)
                }, config).build().asFlow()
            }

            override fun shouldFetch(data: PagedList<Movies>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsMovies>>> =
                remoteDataSource.getMovies()

            override suspend fun saveCallResult(data: List<ResultsMovies>) {
                val movieList = DataMapper.mapResponseToMovieEntities(data)
                localDataSource.addMovies(movieList)
            }
        }.asFlow()


    override fun getAllTVShows(): Flow<Resource<PagedList<TV>>> =
        object : NetworkBoundResource<PagedList<TV>, List<ResultsTV>>(appExecutors) {
            override fun loadFromDB(): Flow<PagedList<TV>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(SIZE)
                    .setPageSize(SIZE)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTVShows().map {
                    DataMapper.mapEntitiesToTVDomain(it)
                }, config).build().asFlow()
            }

            override fun shouldFetch(data: PagedList<TV>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsTV>>> =
                remoteDataSource.getTVShows()

            override suspend fun saveCallResult(data: List<ResultsTV>) {
                val tvList = DataMapper.mapResponseToTVEntities(data)
                localDataSource.addTVShows(tvList)
            }
        }.asFlow()

    // Insert
    override fun addFavMovies(moviesFav: MoviesFav) {
        val data = DataMapper.mapDomainToMoviesFavEntities(moviesFav)
        runBlocking {
            localDataSource.addFavMovies(data)
        }
    }

    override fun addFavTV(tvFav: TVFav) {
        val data = DataMapper.mapDomainToTVFavEntities(tvFav)
        runBlocking {
            localDataSource.addFavTV(data)
        }
    }

    override fun readFavMovies(): Flow<PagedList<MoviesFav>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(SIZE)
            .setPageSize(SIZE)
            .build()
        return LivePagedListBuilder(localDataSource.readFavMovies().map {
            DataMapper.mapEntitiesToMoviesFavDomain(it)
        }, config).build().asFlow()
    }

    override fun readFavTV(): Flow<PagedList<TVFav>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(SIZE)
            .setPageSize(SIZE)
            .build()
        return LivePagedListBuilder(localDataSource.readFavTV().map {
            DataMapper.mapEntitiesToTVFavDomain(it)
        }, config).build().asFlow()
    }

    // Check
    override fun checkFavMovies(movieId: String): Flow<List<MoviesFav>> =
        localDataSource.checkFavMovies(movieId).map {
            DataMapper.mapCheckMovies(it)
        }

    override fun checkFavTV(tvId: String): Flow<List<TVFav>> =
       localDataSource.checkFavTV(tvId).map {
            DataMapper.mapCheckTV(it)
        }

    // Delete
    override fun deleteFavMovies(moviesFav: MoviesFav) {
        val data = DataMapper.mapDomainToMoviesFavEntities(moviesFav)
        runBlocking {
            localDataSource.deleteMovies(data)
        }
    }

    override fun deleteTVMovies(tvFav: TVFav) {
        val data = DataMapper.mapDomainToTVFavEntities(tvFav)
        runBlocking {
            localDataSource.deleteTVShows(data)
        }
    }
}
