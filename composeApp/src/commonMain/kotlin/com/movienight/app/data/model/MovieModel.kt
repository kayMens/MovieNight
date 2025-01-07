package com.movienight.app.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieModel(
    val id: String,
    val title: String,
    val poster: String,
    @SerialName("startYear")
    val year: Int,
    @SerialName("averageRating")
    val rating: Float = 0.0f,
    @SerialName("numVotes")
    val votes: Int = 0,
    @SerialName("description")
    val plot: String = "",
    @SerialName("runtimeMinutes")
    val runtime: Int = 0,
    @SerialName("url")
    val link: String = ""
)