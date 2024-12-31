package com.movienight.app.data.remote.api

import com.movienight.app.data.model.MovieModel

interface MoviesApi {
    suspend fun fetchMovies(): List<MovieModel>
}

class MoviesApiImpl(
    private val client: KtorClient
) : MoviesApi {

    override suspend fun fetchMovies(): List<MovieModel> {
        return KtorClient.get("movies")
    }
}