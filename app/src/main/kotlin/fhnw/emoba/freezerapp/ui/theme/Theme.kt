package fhnw.emoba.freezerapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val AppDarkColors = darkColors(
    primary          =  Color(0xFFad1457),
    primaryVariant   =  Color(0xFF78002e),
    secondary        =  Color(0xFFffeb3b), // Color(0xFF9575cd),
    secondaryVariant =  Color(0xFFffff72), // Color(0xFF65499c),
)
private val AppLightColors = lightColors(
    //Background colors
    primary          = Color(0xFFad1457),
    primaryVariant   = Color(0xFF78002e),
    secondary        = Color(0xFF00796b),
    secondaryVariant = Color(0xFF004c40),
    background       = white1,
    surface          = white2,
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