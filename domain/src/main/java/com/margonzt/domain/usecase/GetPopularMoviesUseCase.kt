package com.margonzt.domain.usecase

import com.margonzt.domain.model.Movie
import com.margonzt.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Flow<Result<List<Movie>>> = flow{
        try {
            emit(Result.success(movieRepository.getPopularMovies()))
        }catch (e: Exception){
            emit(Result.failure(e))
        }
    }

}