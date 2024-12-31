package com.movienight.app

import android.os.Build
import androidx.compose.ui.text.font.Font

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()