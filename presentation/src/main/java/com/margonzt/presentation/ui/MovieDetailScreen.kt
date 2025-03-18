package com.margonzt.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.margonzt.domain.model.Movie
import com.margonzt.presentation.R
import com.margonzt.presentation.extentions.getGenreString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(movie: Movie){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("${movie.name} Details") }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding(), start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = movie.posterPath,
                contentDescription = movie.name,
                placeholder = painterResource(R.drawable.baseline_download_24),
                modifier = Modifier.height(200.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = movie.name, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.headlineLarge)

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Release Date: ${movie.releaseDate}", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = movie.overview, style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Genres: ${movie.getGenreString()?:"Unknown"}", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Preview
@Composable
fun MovieDetailPreview(){
    MovieDetailScreen(Movie(
        id = "12",
        name = "The Phalanx",
        posterPath = "",
        genreIds = listOf(),
        overview = "Letters from the...",
        releaseDate = "2025-03-16"
    ))
}