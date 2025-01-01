package com.movienight.app

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android

actual fun getEngine(): HttpClientEngine {
    return Android.create()
}