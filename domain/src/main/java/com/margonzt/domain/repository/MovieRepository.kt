package com.margonzt.domain.repository

import com.margonzt.domain.model.Movie

interface MovieRepository {
    suspend fun getPopularMovies(page: Int): List<Movie>
}