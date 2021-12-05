package fhnw.emoba.freezerapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import fhnw.emoba.R

private val Roboto = FontFamily(
        Font(R.font.roboto_thin, FontWeight.Thin),
        Font(R.font.roboto_regular, FontWeight.Normal),
        Font(R.font.roboto_bold, FontWeight.Bold),
        Font(R.font.roboto_medium, FontWeight.Medium)
)

private val GoogleSans = FontFamily(
        Font(R.font.googlesans_regular, FontWeight.Normal),
        Font(R.font.googlesans_bold, FontWeight.Bold),
        Font(R.font.googlesans_medium, FontWeight.Medium)
)

val typography = Typography(

        h1 = TextStyle(
                fontFamily    = GoogleSans,
                fontSize      = 96.sp,
                fontWeight    = FontWeight.Bold,
                lineHeight    = 117.sp,
                letterSpacing = (-1.5).sp
        ),
        h2 = TextStyle(
                fontFamily    = GoogleSans,
                fontSize      = 60.sp,
                fontWeight    = FontWeight.Medium,
                lineHeight    = 73.sp,
                letterSpacing = (-0.5).sp
        ),
        h3 = TextStyle(
                fontFamily    = GoogleSans,
                fontSize      = 48.sp,
                fontWeight    = FontWeight.Normal,
                lineHeight    = 59.sp
        ),
        h4 = TextStyle(
                fontFamily    = GoogleSans,
                fontSize      = 30.sp,
                fontWeight    = FontWeight.Bold,
                lineHeight    = 37.sp
        ),
        h5 = TextStyle(
                fontFamily    = GoogleSans,
                fontSize      = 24.sp,
                fontWeight    = FontWeight.Medium,
                lineHeight    = 29.sp
        ),
        h6 = TextStyle(
                fontFamily    = GoogleSans,
                fontSize      = 20.sp,
                fontWeight    = FontWeight.Normal,
                letterSpacing = 0.15.sp,
                lineHeight    = 24.sp
        ),
        subtitle1 = TextStyle(
                fontFamily    = Roboto,
                fontSize      = 16.sp,
                fontWeight    = FontWeight.SemiBold,
                lineHeight    = 24.sp,
                letterSpacing = 0.15.sp
        ),
        subtitle2 = TextStyle(
                fontFamily    = Roboto,
                fontSize      = 14.sp,
                fontWeight    = FontWeight.Bold,
                lineHeight    = 24.sp,
                letterSpacing = 0.1.sp
        ),
        body1 = TextStyle(
                fontFamily    = Roboto,
                fontSize      = 16.sp,
                fontWeight    = FontWeight.Normal,
                lineHeight    = 28.sp,
                letterSpacing = 0.15.sp
        ),
        body2 = TextStyle(
                fontFamily    = Roboto,
                fontSize      = 14.sp,
                fontWeight    = FontWeight.Medium,
                lineHeight    = 20.sp,
                letterSpacing = 0.1.sp
        ),
        button = TextStyle(
                fontFamily    = Roboto,
                fontSize      = 14.sp,
                fontWeight    = FontWeight.SemiBold,
                lineHeight    = 16.sp,
                letterSpacing = 1.25.sp
        ),
        caption = TextStyle(
                fontFamily    = Roboto,
                fontSize      = 12.sp,
                fontWeight    = FontWeight.Normal,
                lineHeight    = 16.sp,
                letterSpacing = 0.4.sp
        ),
        overline = TextStyle(
                fontFamily    = Roboto,
                fontSize      = 12.sp,
                fontWeight    = FontWeight.SemiBold,
                lineHeight    = 16.sp,
                letterSpacing = 1.sp
        )

)