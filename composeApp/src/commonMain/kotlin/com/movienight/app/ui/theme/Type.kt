package com.movienight.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import movienight.composeapp.generated.resources.Res
import movienight.composeapp.generated.resources.actor
import movienight.composeapp.generated.resources.aladin
import org.jetbrains.compose.resources.Font

@Composable
fun bodyFontFamily() = FontFamily(
    Font(Res.font.actor)
)

@Composable
fun displayFontFamily() = FontFamily(
    Font(Res.font.aladin)
)

@Composable
fun typography() = Typography().run {
    val bodyFont = bodyFontFamily()
    val displayFont = displayFontFamily()

    copy(
        displayLarge = displayLarge.copy(fontFamily = displayFont),
        displayMedium = displayMedium.copy(fontFamily = displayFont),
        displaySmall = displaySmall.copy(fontFamily = displayFont),
        headlineLarge = headlineLarge.copy(fontFamily = displayFont),
        headlineMedium = headlineMedium.copy(fontFamily = displayFont),
        headlineSmall = headlineSmall.copy(fontFamily = displayFont),
        titleLarge = titleLarge.copy(fontFamily = displayFont),
        titleMedium = titleMedium.copy(fontFamily = displayFont),
        titleSmall = titleSmall.copy(fontFamily = displayFont),
        bodyLarge = bodyLarge.copy(fontFamily = bodyFont),
        bodyMedium = bodyMedium.copy(fontFamily = bodyFont),
        bodySmall = bodySmall.copy(fontFamily = bodyFont),
        labelLarge = labelLarge.copy(fontFamily = bodyFont),
        labelMedium = labelMedium.copy(fontFamily = bodyFont),
        labelSmall = labelSmall.copy(fontFamily = bodyFont),
    )
}

