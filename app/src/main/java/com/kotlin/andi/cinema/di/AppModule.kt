package com.kotlin.andi.cinema.di

import com.kotlin.andi.cinema.favorite.FavoriteViewModel
import com.kotlin.andi.cinema.home.HomeViewModel
import com.kotlin.andi.core.domain.usecase.MoviesInteractor
import com.kotlin.andi.core.domain.usecase.MoviesUseCase
import com.kotlin.andi.core.ui.viewmodel.DetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MoviesUseCase> { MoviesInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}
