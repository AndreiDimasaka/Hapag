package com.example.hapag.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp

@Composable
fun Dp.responsiveSize(): Dp {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp

    return when {
        screenWidthDp < 480 -> this * 0.8f
        screenWidthDp < 720 -> this
        else -> this * 1.2f
    }
}