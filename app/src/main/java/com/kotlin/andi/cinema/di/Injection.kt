package com.kotlin.andi.cinema.di

import com.kotlin.andi.cinema.data.source.MovieRepository
import com.kotlin.andi.cinema.data.source.remote.RemoteDataSource
import com.kotlin.andi.cinema.utils.JsonHelper

object Injection {
    fun provideRepository(): MovieRepository {
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper())
        return MovieRepository.getInstance(remoteDataSource)
    }
}
