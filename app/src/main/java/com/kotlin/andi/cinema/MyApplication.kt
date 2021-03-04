@file:Suppress("unused")

package com.kotlin.andi.cinema

import android.app.Application
import com.kotlin.andi.cinema.di.useCaseModule
import com.kotlin.andi.cinema.di.viewModelModule
import com.kotlin.andi.core.di.databaseModule
import com.kotlin.andi.core.di.networkModule
import com.kotlin.andi.core.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}
