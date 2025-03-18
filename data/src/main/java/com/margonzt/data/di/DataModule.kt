package com.margonzt.data.di

import com.margonzt.data.remote.ApiClient
import com.margonzt.data.remote.MovieApiService
import com.margonzt.data.repository.MovieRepositoryImpl
import com.margonzt.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideMovieApiService(): MovieApiService {
        return ApiClient.movieApiService
    }

    @Provides
    @Singleton
    fun provideMovieRepository(apiService: MovieApiService): MovieRepository {
        return MovieRepositoryImpl(apiService)
    }
}