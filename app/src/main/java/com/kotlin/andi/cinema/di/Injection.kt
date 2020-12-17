package com.kotlin.andi.cinema.di

import android.content.Context
import com.kotlin.andi.cinema.data.MovieRepository
import com.kotlin.andi.cinema.data.source.local.LocalDataSource
import com.kotlin.andi.cinema.data.source.local.room.MovieDatabase
import com.kotlin.andi.cinema.data.source.remote.RemoteDataSource
import com.kotlin.andi.cinema.utils.AppExecutors
import com.kotlin.andi.cinema.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): MovieRepository {

        val database = MovieDatabase.getDatabase(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper())
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}
