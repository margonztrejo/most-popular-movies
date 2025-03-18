package com.margonzt.presentation.extentions

import com.margonzt.domain.model.Movie
import com.margonzt.presentation.model.GENRES_MAP

fun Movie.getGenreString(): String? {
    if(this.genreIds.isEmpty()){
        return null
    }

    return this.genreIds.map { GENRES_MAP[it] }.joinToString(", ")
}