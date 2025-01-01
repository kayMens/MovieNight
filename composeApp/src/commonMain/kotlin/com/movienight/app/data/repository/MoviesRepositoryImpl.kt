package com.movienight.app.data.repository

import com.movienight.app.data.model.MovieModel
import com.movienight.app.data.remote.MoviesDataSource
import com.movienight.app.domain.model.Movie
import com.movienight.app.domain.repository.MoviesRepository

class MoviesRepositoryImpl(
    private val moviesDataSource: MoviesDataSource
) : MoviesRepository {

    override suspend fun getMovies(): List<Movie> {
        return moviesDataSource.getMovies().map {
            it.toMovie()
        }
    }
}

fun MovieModel.toMovie() = Movie(
    id = id,
    title = title,
    poster = poster
)