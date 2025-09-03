package com.beratbaran.loopa.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.beratbaran.loopa.R

val JakartaFont = FontFamily(
    Font(R.font.jakarta_medium, FontWeight.Medium),
    Font(R.font.jakarta_semibold, FontWeight.SemiBold)
)
val Typography = Typography(

    displaySmall = TextStyle(
        fontFamily = JakartaFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 30.sp
    ),

    displayLarge = TextStyle(
        fontFamily = JakartaFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 40.sp,
        lineHeight = 48.sp
    ),

    bodyLarge = TextStyle(
        fontFamily = JakartaFont,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 20.sp
    ),

    bodyMedium = TextStyle(
        fontFamily = JakartaFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 20.sp
    ),

    bodySmall = TextStyle(
        fontFamily = JakartaFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 16.sp
    ),

    titleLarge = TextStyle(
        fontFamily = JakartaFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 28.sp
    ),

    titleMedium = TextStyle(
        fontFamily = JakartaFont,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 18.sp
    ),

    labelMedium = TextStyle(
        fontFamily = JakartaFont,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 14.sp
    ),

    titleSmall = TextStyle(
        fontFamily = JakartaFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 24.sp
    ),

    labelLarge = TextStyle(
        fontFamily = JakartaFont,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 16.sp

    ),

    labelSmall = TextStyle(
        fontFamily = JakartaFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 14.sp
    ),
)

// TYPOGRAPHY DAHA DOĞRU BİR HALE GETİRİLDİ, VERİLER TAMAMLANIYOR... [20/100]. (03.09.2025 - 19.07)