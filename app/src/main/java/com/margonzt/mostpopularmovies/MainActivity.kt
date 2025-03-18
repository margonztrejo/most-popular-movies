package com.margonzt.mostpopularmovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.margonzt.mostpopularmovies.ui.theme.MostPopularMoviesTheme
import com.margonzt.presentation.ui.MostPopularMoviesScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MostPopularMoviesTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Most Popular Movies") }
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { paddingValues ->
                    MostPopularMoviesScreen(modifier = Modifier.padding(top = paddingValues.calculateTopPadding()))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MostPopularMoviesTheme {
        Greeting("Android")
    }
}