package com.margonzt.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.margonzt.presentation.ui.MostPopularMoviesScreen
import com.margonzt.presentation.ui.theme.MostPopularMoviesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MostPopularMoviesNavigation(){
    val navController = rememberNavController()

    MostPopularMoviesTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Most Popular Movies") }
                )
            },
            modifier = Modifier.fillMaxSize()
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = MOVIES_LIST_ROUTE
            ){
                //Here could be added nested graphs if we had more big features
                composable(route = MOVIES_LIST_ROUTE){
                    MostPopularMoviesScreen(modifier = Modifier.padding(paddingValues)){ movie ->
                        navController.navigate(MOVIE_DETAIL_ROUTE)
                    }
                }
                composable(route = MOVIE_DETAIL_ROUTE){

                }
            }
        }
    }
}