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

    private var currentPage = 1
    private var isLoading = false
    private var isLastPage = false

    init {
        loadMostPopularMovies()
    }

    fun loadMostPopularMovies(){
        if(isLoading || isLastPage){
            return
        }
        isLoading = true

        viewModelScope.launch {
            getPopularMoviesUseCase(currentPage).collect{result ->
                result.fold(
                    onSuccess = {
                        if(it.isEmpty()){
                            isLastPage = true
                        }
                        _movies.value += it
                        currentPage ++
                        isLoading = false
                    },
                    onFailure = {
                        _error.value = it.message
                    }
                )
            }
        }
    }
}