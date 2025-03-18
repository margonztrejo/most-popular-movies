package com.margonzt.data.repository

import com.margonzt.data.mapper.toDomain
import com.margonzt.data.remote.MovieApiService
import com.margonzt.domain.model.Movie
import com.margonzt.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: MovieApiService
): MovieRepository {
    override suspend fun getPopularMovies(page: Int): List<Movie> {
        return apiService.getPopularMovies(page = page).results.map { it.toDomain() }
    }
}