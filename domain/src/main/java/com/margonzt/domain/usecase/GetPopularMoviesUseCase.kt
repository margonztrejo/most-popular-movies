package com.margonzt.domain.usecase

import com.margonzt.domain.model.Movie
import com.margonzt.domain.repository.MovieRepository
import com.margonzt.domain.runFlowSafely
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(page: Int): Flow<Result<List<Movie>>> = runFlowSafely {
        movieRepository.getPopularMovies(page = page)
    }

}