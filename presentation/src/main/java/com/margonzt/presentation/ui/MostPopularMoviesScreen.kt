package com.margonzt.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.margonzt.domain.model.Movie
import com.margonzt.presentation.viewmodel.MostPopularMoviesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MostPopularMoviesScreen(
    viewModel: MostPopularMoviesViewModel = hiltViewModel(),
    goToDetail: (Movie) -> Unit
){
    val movies by viewModel.movies.collectAsState()
    val error by viewModel.error.collectAsState()

    MostPopularMovies(movies, error, goToDetail)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MostPopularMovies(
    movies: List<Movie>,
    error: String?,
    goToDetail: (Movie) -> Unit
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Most Popular Movies") }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize().padding(top = paddingValues.calculateTopPadding()),
            contentAlignment = Alignment.Center
        ) {
            if (error != null) {
                Text(text = "Error: $error", color = Color.Red)
            } else if (movies.isEmpty()) {
                CircularProgressIndicator()
            } else {
                MovieList(movies, goToDetail)
            }
        }
    }
}


@Composable
fun MovieList(movies: List<Movie>, gotToDetail: (Movie) -> Unit) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(items = movies) { movie ->
            MovieItem(movie, gotToDetail)
        }
    }
}

@Composable
fun MovieItem(movie: Movie, gotToDetail: (Movie) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
            contentDescription = movie.name,
            modifier = Modifier.height(50.dp),
            contentScale = ContentScale.Fit
        )
        Column(
            modifier = Modifier.fillMaxWidth().padding(8.dp).clickable { gotToDetail(movie) }
        ) {
            Text(text = "${movie.name}: ${movie.releaseDate}", fontWeight = FontWeight.Bold)
            Text(text = movie.overview, maxLines = 3)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MostPopularMoviesScreenPreview(){
    MostPopularMovies(
        movies = listOf(
            Movie(name ="The Phalanx", posterPath = "", genreIds = listOf(), overview = "Letters from the...", releaseDate = "2025-03-16")
        ),
        error =null,
        goToDetail = {}
    )
}