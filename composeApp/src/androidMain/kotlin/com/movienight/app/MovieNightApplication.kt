package com.movienight.app

import android.app.Application
import org.koin.core.context.startKoin

class MovieNightApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

        }
    }
}