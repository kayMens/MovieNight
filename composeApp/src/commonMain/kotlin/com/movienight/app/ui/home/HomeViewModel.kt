package com.movienight.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movienight.app.domain.model.Movie
import com.movienight.app.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val movies = try {
                moviesRepository.getMovies()
            } catch (ex: Exception) {
                emptyList()
            }
            _uiState.update {
                it.copy(
                    isLoading = false,
                    movies = movies
                )
            }
        }
    }
}

data class HomeState(
    val isLoading: Boolean = true,
    val movies: List<Movie> = emptyList()
)