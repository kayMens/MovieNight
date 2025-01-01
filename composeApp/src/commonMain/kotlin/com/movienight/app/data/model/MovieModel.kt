package com.movienight.app.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieModel(
    val id: String,
    val title: String,
    val poster: String
)