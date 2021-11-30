package fhnw.emoba.freezerapp.ui.theme


import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import fhnw.emoba.R


private val Roboto = FontFamily(
        Font(R.font.roboto_thin, FontWeight.Thin),
        Font(R.font.roboto_regular, FontWeight.Normal),
        Font(R.font.roboto_bold, FontWeight.Bold),
        Font(R.font.roboto_medium, FontWeight.Medium)
)

val typography = Typography(
        defaultFontFamily = Roboto
)