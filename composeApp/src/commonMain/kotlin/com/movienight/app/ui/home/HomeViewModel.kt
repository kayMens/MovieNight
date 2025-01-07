package com.movienight.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movienight.app.domain.model.Movie
import com.movienight.app.domain.repository.MoviesRepository
import com.movienight.app.ui.extension.backInTime
import com.movienight.app.ui.extension.cover
import com.movienight.app.ui.extension.featured
import com.movienight.app.ui.extension.nowWatching
import com.movienight.app.ui.extension.shellysFinest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeState())
    val uiState = _uiState.asStateFlow()

    fun init() {
        viewModelScope.launch {
            val movies = try {
                moviesRepository.getMovies()
            } catch (ex: Exception) {
                println(ex)
                emptyList()
            }
            _uiState.update {
                it.copy(
                    isLoading = false,
                    carousel = movies.featured(),
                    todayPick = movies.cover(),
                    shellysFinest = movies.shellysFinest(),
                    nowWatching = movies.nowWatching(),
                    backInTime = movies.backInTime()
                )
            }
        }
    }
}

data class HomeState(
    val isLoading: Boolean = true,
    val todayPick: Movie? = null,
    val carousel: List<Movie> = emptyList(),
    val nowWatching: List<Movie> = emptyList(),
    val backInTime: List<Movie> = emptyList(),
    val shellysFinest: List<Movie> = emptyList(),
)