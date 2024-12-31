package com.movienight.app.data.remote

import com.movienight.app.data.model.MovieModel
import com.movienight.app.data.remote.api.MoviesApi

interface MoviesDataSource {
    suspend fun getMovies(): List<MovieModel>
}

class MoviesDataSourceImpl(
    private val moviesApi: MoviesApi
): MoviesDataSource {

    override suspend fun getMovies(): List<MovieModel> {
        return moviesApi.fetchMovies()
    }

}