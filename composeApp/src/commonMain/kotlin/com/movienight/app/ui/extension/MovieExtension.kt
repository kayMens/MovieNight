package com.movienight.app.ui.extension

import com.movienight.app.domain.model.Movie

fun List<Movie>.featured(): List<Movie> {
    return filter { it.year > 2020 && it.votes > 200000 }
        .shuffled()
        .take(10)
}

fun List<Movie>.shellysFinest(): List<Movie> {
    return filter { it.year > 2000 && it.rating > 8.5 }
        .shuffled()
        .take(12)
}

fun List<Movie>.nowWatching(): List<Movie> {
    return filter { it.year > 2021 }
        .shuffled()
        .take(12)
}

fun List<Movie>.backInTime(): List<Movie> {
    return filter { it.year < 1980 }
        .shuffled()
        .take(12)
}

fun List<Movie>.cover(): Movie {
    return filter { it.year > 2017 }
        .shuffled()
        .first()
}