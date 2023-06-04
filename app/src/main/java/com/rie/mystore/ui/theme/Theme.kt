package com.rie.mystore.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = cyan200,
    primaryVariant = cyan700,
    secondary = blue500
)

private val LightColorPalette = lightColors(
    primary = cyan500,
    primaryVariant = cyan700,
    secondary = blue500
)

@Composable
fun MyStoreTheme(
    content: @Composable () -> Unit
) {
    val isDarkTheme = isSystemInDarkTheme()

    MaterialTheme(
        colors = if (isDarkTheme) DarkColorPalette else LightColorPalette,
        content = content
    )
}
