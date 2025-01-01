package com.movienight.app

import android.app.Application
import com.movienight.app.di.commonModule
import com.movienight.app.di.viewModelModule
import org.koin.core.context.startKoin

class MovieNightApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(commonModule, viewModelModule)
        }
    }
}