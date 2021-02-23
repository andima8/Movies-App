package com.kotlin.andi.cinema.di

import com.kotlin.andi.cinema.core.domain.usecase.MoviesInteractor
import com.kotlin.andi.cinema.core.domain.usecase.MoviesUseCase
import com.kotlin.andi.cinema.core.ui.viewmodel.DetailViewModel
import com.kotlin.andi.cinema.core.ui.viewmodel.FavoriteViewModel
import com.kotlin.andi.cinema.core.ui.viewmodel.HomeViewModel
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
