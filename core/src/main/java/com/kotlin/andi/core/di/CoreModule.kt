package com.kotlin.andi.core.di

import androidx.room.Room
import com.kotlin.andi.core.BuildConfig
import com.kotlin.andi.core.api.ApiService
import com.kotlin.andi.core.data.MovieRepository
import com.kotlin.andi.core.data.source.local.LocalDataSource
import com.kotlin.andi.core.data.source.local.room.MovieDatabase
import com.kotlin.andi.core.data.source.remote.RemoteDataSource
import com.kotlin.andi.core.domain.repository.IMoviesRepository
import com.kotlin.andi.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.*

val databaseModule = module {
    factory { get<MovieDatabase>().movieDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java, "movie_database"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMoviesRepository> { MovieRepository(get(), get(), get()) }
}
