package com.joesemper.movingcarcompose.di

import com.joesemper.movingcarcompose.data.datasource.RouteRepositoryImpl
import com.joesemper.movingcarcompose.data.repository.RouteRepository
import com.joesemper.movingcarcompose.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<RouteRepository> { RouteRepositoryImpl() }
}

val mainActivityModule = module {
    viewModel { MainViewModel(get()) }
}