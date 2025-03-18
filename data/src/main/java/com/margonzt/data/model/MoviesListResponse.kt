package com.margonzt.data.model

import com.google.gson.annotations.SerializedName

data class MoviesListResponse(
    @SerializedName("results")
    val results: List<MovieDto>
)