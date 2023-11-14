package com.example.sensoryshifters;

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.sensoryshifters.theme.*
private val DarkColorScheme = darkColors(
    primary = Blue100,
    primaryVariant = Orange700,
    secondary = Yellow200,
    background = Orange700,
    surface = Orange200,
    error = Color.Red,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.Black,
    onError = Color.White
)

private val LightColorScheme = lightColors(
    primary = Orange500,
    primaryVariant = Orange700,
    secondary = Yellow200,
    background = Color.White,
    surface = Blue100,
    error = Color.Red,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Black100,
    onSurface = Color.Black,
    onError = Color.White
)







@Composable
fun SensoryShiftersTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        content = content
    )
}