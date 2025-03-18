package com.margonzt.presentation.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.margonzt.domain.model.Movie
import com.margonzt.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MostPopularMoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies = _movies.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    init {
        fetchMostPopularMovies()
    }

    private fun fetchMostPopularMovies(){
        viewModelScope.launch {
            getPopularMoviesUseCase().collect{result ->
                result.fold(
                    onSuccess = {
                        _movies.value = it
                    },
                    onFailure = {
                        _error.value = it.message
                    }
                )
            }
        }
    }
}