package com.example.gratitude.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = DarkPink,
    background = BackgroundBlack,
    surface = BackgroundBlack,
    onPrimary = White,
    onBackground = White,
    onSurface = White
)

private val LightColorPalette = lightColors(
    primary = DarkPink,
    background = White,
    surface = White,
    onPrimary = BackgroundBlack,
    onBackground = BackgroundBlack,
    onSurface = BackgroundBlack
)

@Composable
fun GratitudeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = AppTypography,
        shapes = Shapes,
        content = content
    )
}
