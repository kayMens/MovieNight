package com.movienight.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform