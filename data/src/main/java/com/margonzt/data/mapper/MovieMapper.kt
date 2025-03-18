package com.margonzt.data.mapper

import com.margonzt.data.model.MovieDto
import com.margonzt.domain.model.Movie

fun MovieDto.toDomain(): Movie {
    val posterUrl = if(posterPath == null){
        "https://www.meme-arsenal.com/memes/3431edd1e935bb24f164d253138de648.jpg"
    }else{
        "https://image.tmdb.org/t/p/w500${posterPath}"
    }
    return Movie(
        id = id,
        name = name,
        posterPath = posterUrl,
        genreIds = genreIds,
        overview = overview,
        releaseDate = releaseDate
    )
}