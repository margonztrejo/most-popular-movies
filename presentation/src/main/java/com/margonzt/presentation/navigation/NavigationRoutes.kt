package com.margonzt.presentation.navigation

import android.net.Uri
import com.google.gson.Gson
import com.margonzt.domain.model.Movie

private const val MOVIES_LIST_ROUTE = "movies_list_route"

private const val MOVIE_DETAIL_ARG = "/{movie}"
private const val MOVIE_DETAIL_ROUTE = "movie_detail_route"

sealed class NavigationRoutes(val route: String){
    data object MoviesList: NavigationRoutes(MOVIES_LIST_ROUTE)
    data object MovieDetail: NavigationRoutes(MOVIE_DETAIL_ROUTE + MOVIE_DETAIL_ARG){
        fun createUrlWithArgs(movie: Movie): String{
            val movieJson = Uri.encode(Gson().toJson(movie))
            return "$MOVIE_DETAIL_ROUTE${MOVIE_DETAIL_ARG.replace("{movie}",movieJson)}"
        }
    }
}