package com.margonzt.data.mapper

import com.margonzt.data.model.MovieDto
import com.margonzt.domain.model.Movie

fun MovieDto.toDomain(): Movie {
    return Movie(
        name = name,
        posterPath = posterPath,
        genreIds = genreIds,
        overview = overview,
        releaseDate = releaseDate
    )
}