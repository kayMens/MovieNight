package com.movienight.app

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.js.Js

actual fun getEngine(): HttpClientEngine {
    return Js.create()
}