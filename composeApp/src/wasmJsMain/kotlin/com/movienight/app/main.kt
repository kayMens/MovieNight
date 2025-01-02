package com.movienight.app

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.movienight.app.di.commonModule
import com.movienight.app.di.viewModelModule
import kotlinx.browser.document
import org.koin.compose.KoinApplication

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        KoinApplication(
            application = { modules(commonModule, viewModelModule) }
        ) {
            App()
        }
    }
}