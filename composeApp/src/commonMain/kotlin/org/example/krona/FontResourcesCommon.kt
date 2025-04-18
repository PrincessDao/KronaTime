package org.example.krona

import androidx.compose.ui.text.font.FontFamily

expect fun GetRalewayFontFamily(): FontFamily

val raleway = GetRalewayFontFamily()