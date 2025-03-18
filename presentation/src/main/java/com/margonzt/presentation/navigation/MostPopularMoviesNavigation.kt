package com.margonzt.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.margonzt.domain.model.Movie
import com.margonzt.presentation.ui.MostPopularMoviesScreen
import com.margonzt.presentation.ui.MovieDetailScreen
import com.margonzt.presentation.ui.theme.MostPopularMoviesTheme

@Composable
fun MostPopularMoviesNavigation(){
    val navController = rememberNavController()

    MostPopularMoviesTheme {
        NavHost(
            navController = navController,
            startDestination = NavigationRoutes.MoviesList.route
        ){
            //Here could be added nested graphs if we had more big features
            composable(route = NavigationRoutes.MoviesList.route){
                MostPopularMoviesScreen{ movie ->
                    navController.navigate(NavigationRoutes.MovieDetail.createUrlWithArgs(movie))
                }
            }
            composable(route = NavigationRoutes.MovieDetail.route){ navBackStackEntry ->
                val movieJson = navBackStackEntry.arguments?.getString("movie")
                val movie = Gson().fromJson(movieJson, Movie::class.java)
                MovieDetailScreen(movie = movie)
            }
        }
    }
}