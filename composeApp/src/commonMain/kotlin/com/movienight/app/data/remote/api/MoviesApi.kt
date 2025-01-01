package com.movienight.app.data.remote.api

import com.movienight.app.data.model.MovieModel
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.serializer

interface MoviesApi {
    suspend fun fetchMovies(): List<MovieModel>
}

class MoviesApiImpl(
    private val client: ApiClient
) : MoviesApi {

    @OptIn(InternalSerializationApi::class)
    override suspend fun fetchMovies(): List<MovieModel> {
        val serializer = ListSerializer(MovieModel::class.serializer())
        return client.get("movies", serializer)
    }
}
