package com.wayqast.gateway.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val WayqastGreen = Color(0xFF22C55E)

private val DarkColorScheme = darkColorScheme(
    primary = WayqastGreen,
    onPrimary = Color(0xFF0A1A0F),
    secondary = WayqastGreen,
    error = Color(0xFFEF6461),
    background = Color(0xFF1A1A1A),
    onBackground = Color(0xFFFAFAFA),
    surface = Color(0xFF242424),
    onSurface = Color(0xFFFAFAFA)
)

private val LightColorScheme = lightColorScheme(
    primary = WayqastGreen,
    onPrimary = Color.White,
    secondary = WayqastGreen,
    error = Color(0xFFDC2626),
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF1A1A1A),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF1A1A1A)
)

@Composable
fun WayqastTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
