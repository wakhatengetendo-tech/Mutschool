package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme =
  darkColorScheme(
    primary = AyNavyLight,
    secondary = AyGoldSecondary,
    tertiary = AyCrimsonAccent,
    background = AyDarkBackground,
    surface = AyDarkSurface,
    onPrimary = Color.White,
    onSecondary = AyNavyDark,
    onBackground = AyDarkOnSurface,
    onSurface = AyDarkOnSurface
  )

private val LightColorScheme =
  lightColorScheme(
    primary = AyNavyPrimary,
    secondary = AyGoldSecondary,
    tertiary = AyCrimsonAccent,
    background = AyLightBackground,
    surface = AyLightSurface,
    onPrimary = Color.White,
    onSecondary = AyNavyDark,
    onBackground = AyLightOnSurface,
    onSurface = AyLightOnSurface
  )

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  // Dynamic color is available on Android 12+
  dynamicColor: Boolean = true,
  content: @Composable () -> Unit,
) {
  val colorScheme =
    when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }

      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }

  MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
