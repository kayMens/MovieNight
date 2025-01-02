package com.movienight.app.domain.model

data class Movie(
    val id: String,
    val title: String,
    val poster: String,
    val year: Int,
    val rating: Float = 0.0f,
    val votes: Int = 0,
    val plot: String? = ""
)
