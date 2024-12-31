package com.movienight.app

import androidx.compose.runtime.Composable
import com.movienight.app.ui.home.HomeScreen
import com.movienight.app.ui.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    isDarkTheme: Boolean = false
) {
    AppTheme(
        darkTheme = isDarkTheme
    ) {
        HomeScreen()
    }
}