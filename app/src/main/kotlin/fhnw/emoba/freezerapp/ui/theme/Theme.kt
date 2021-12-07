package fhnw.emoba.freezerapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// https://material.io/resources/color/
private val AppDarkColors = darkColors(
    primary          =  Color(0xFFad1457), // Pink800
    primaryVariant   =  Color(0xFF78002e), // Dark
    secondary        =  Color(0xFFffeb3b), // Yellow500
    secondaryVariant =  Color(0xFFffff72), // Light
)
private val AppLightColors = lightColors(
    //Background colors
    primary          = Color(0xFFad1457), // Pink800
    primaryVariant   = Color(0xFF78002e), // Dark
    secondary        = Color(0xFF303f9f), // Indigo700
    secondaryVariant = Color(0xFF001970), // Dark
    background       = white1,
    surface          = white1,
    error            = red,

    //Typography and icon colors
    onPrimary        = Color.White,
    onSecondary      = Color.Black,
    onBackground     = Color.Black,
    onSurface        = Color.Black,
    onError          = Color.White
)

@Composable
fun FreezerAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        AppDarkColors
    } else {
        AppLightColors
    }

    MaterialTheme(
            colors     = colors,
            typography = typography,
            shapes     = shapes,
            content    = content
    )
}