package com.movienight.app.domain.model

data class Movie(
    val id: String,
    val title: String,
    val poster: String,
    val year: Int,
    val rating: Float = 0.0f,
    val votes: Int = 0,
    val plot: String = "",
    val runtime: Int = 0,
    val link: String = ""
) {
    val runtimeString: String
        get() {
            val hours = runtime / 60
            val minutes = runtime % 60
            return "${hours}h ${minutes}m"
        }
}
