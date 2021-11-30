package fhnw.emoba.freezerapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val AppDarkColors = darkColors(
//    primary = gray500,
//    primaryVariant = gray600,
//    secondary = blue400,

)

private val AppLightColors = lightColors(
    //Background colors
        primary        = amber400,
        primaryVariant = amber600,
        secondary      = purple200,
        
        secondaryVariant = Color(0xFF03DAC6),
        background       = Color(0xFFF1F5FE),
        surface          = Color(0xFFFFFFFA), //Color(0xFFF8FCFD),
        error            = Color(0xFFB00020),

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