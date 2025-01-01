package com.movienight.app.di

import com.movienight.app.data.remote.MoviesDataSource
import com.movienight.app.data.remote.MoviesDataSourceImpl
import com.movienight.app.data.remote.api.ApiClient
import com.movienight.app.data.remote.api.LocalApiClient
import com.movienight.app.data.remote.api.MoviesApi
import com.movienight.app.data.remote.api.MoviesApiImpl
import com.movienight.app.data.repository.MoviesRepositoryImpl
import com.movienight.app.domain.repository.MoviesRepository
import com.movienight.app.ui.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val commonModule = module {
    single<ApiClient> { LocalApiClient }
    single<MoviesApi> { MoviesApiImpl(get()) }
    single<MoviesDataSource> { MoviesDataSourceImpl(get()) }
    single<MoviesRepository> { MoviesRepositoryImpl(get()) }
}

val viewModelModule = module {
    viewModelOf(::HomeViewModel)
}