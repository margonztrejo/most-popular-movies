package com.margonzt.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

inline fun <T> runFlowSafely(
    crossinline apiCall: suspend () -> T
): Flow<Result<T>> = flow {
    try{
        val response = apiCall()
        emit(Result.success(response))
    }catch (e: IOException){
        emit(Result.failure(Exception("No internet connection")))
    }catch (e: Exception){
        emit(Result.failure(Exception("An unexpected error occurred")))
    }//Here we can add more exceptions
}