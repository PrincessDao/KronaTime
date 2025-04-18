package org.example.krona

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

actual fun GetRalewayFontFamily(): FontFamily {
    return FontFamily(
        Font(R.font.raleway_ultralight, FontWeight.W100, FontStyle.Normal),
        Font(R.font.raleway_ultralight_italic, FontWeight.W100, FontStyle.Italic),
        Font(R.font.raleway_thin, FontWeight.W200, FontStyle.Normal),
        Font(R.font.raleway_thin_italic, FontWeight.W200, FontStyle.Italic),
        Font(R.font.raleway_light, FontWeight.W300, FontStyle.Normal),
        Font(R.font.raleway_light_italic, FontWeight.W300, FontStyle.Italic),
        Font(R.font.raleway_regular, FontWeight.W400, FontStyle.Normal),
        Font(R.font.raleway_regular_italic, FontWeight.W400, FontStyle.Italic),
        Font(R.font.raleway_medium, FontWeight.W500, FontStyle.Normal),
        Font(R.font.raleway_medium_italic, FontWeight.W500, FontStyle.Italic),
        Font(R.font.raleway_semi_bold, FontWeight.W600, FontStyle.Normal),
        Font(R.font.raleway_semi_bold_italic, FontWeight.W600, FontStyle.Italic),
        Font(R.font.raleway_bold, FontWeight.W700, FontStyle.Normal),
        Font(R.font.raleway_bold_italic, FontWeight.W700, FontStyle.Italic),
        Font(R.font.raleway_heavy, FontWeight.W800, FontStyle.Normal),
        Font(R.font.raleway_heavy_italic, FontWeight.W800, FontStyle.Italic),
        Font(R.font.raleway_black, FontWeight.W900, FontStyle.Normal),
        Font(R.font.raleway_black_italic, FontWeight.W900, FontStyle.Italic),
    )
}