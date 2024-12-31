package com.movienight.app.domain.repository

import com.movienight.app.domain.model.Movie

interface MoviesRepository {
    suspend fun getMovies(): List<Movie>
}