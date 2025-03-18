package com.margonzt.domain.model

data class Movie(
    val name: String,
    val posterPath: String,
    val genreIds: List<Int>,
    val overview: String,
    val releaseDate: String
)