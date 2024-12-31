package com.movienight.app

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import com.movienight.app.ui.home.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        HomeScreen()
    }
}