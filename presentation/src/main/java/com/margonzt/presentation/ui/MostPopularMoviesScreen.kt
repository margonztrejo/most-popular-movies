package com.margonzt.presentation.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MostPopularMoviesScreen(){
    Text("Hello World From Presentation Layer")
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MostPopularMoviesScreenPreview(){
    MostPopularMoviesScreen()
}