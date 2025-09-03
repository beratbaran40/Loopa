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

    //Onboarding Screen

    titleMedium = TextStyle(
        fontFamily = JakartaFont,
        fontWeight = FontWeight.SemiBold, //onboarding Screen Logo
        fontSize = 28.sp,
        lineHeight = 30.sp
    ),

    headlineLarge = TextStyle(
        fontFamily = JakartaFont,
        fontWeight = FontWeight.SemiBold, // onboarding karşılama mesajı
        fontSize = 40.sp,
        lineHeight = 48.sp
    ),

    bodyMedium = TextStyle(
        fontFamily = JakartaFont,
        fontWeight = FontWeight.Medium, // onboarding açıklama yazısı - homepage searchbar
        fontSize = 16.sp,
        lineHeight = 20.sp
    ),

    labelLarge = TextStyle(
        fontFamily = JakartaFont,
        fontWeight = FontWeight.SemiBold, // onboarding buton yazısı - detay sayfasındaki konum detayları
        fontSize = 16.sp,
        lineHeight = 20.sp,

        ),

    labelSmall = TextStyle(
        fontFamily = JakartaFont,
        fontWeight = FontWeight.Medium, // onboarding register/login yazısı // homepage'deki "See All" yazısı
        fontSize = 14.sp,
        lineHeight = 18.sp
    ),

    // homepage/place screen

    headlineMedium = TextStyle(
        fontFamily = JakartaFont,
        fontWeight = FontWeight.SemiBold, // Homepage Karşılama mesajı
        fontSize = 24.sp,
        lineHeight = 30.sp
    ),

    labelMedium = TextStyle(
        fontFamily = JakartaFont,
        fontWeight = FontWeight.Medium, // homepage durum etiketi
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),

    bodySmall = TextStyle(
        fontFamily = JakartaFont,
        fontWeight = FontWeight.Medium, // homepage kartlardaki alt bilgi (konum)
        fontSize = 12.sp,
        lineHeight = 14.sp
    ),

    titleLarge = TextStyle(
        fontFamily = JakartaFont,
        fontWeight = FontWeight.SemiBold, // detay ekranındaki "Overview" başlığı ve "Photos" Başlığı
        fontSize = 20.sp,
        lineHeight = 24.sp

    )
)

val LabelLargeFilterTags = Typography.labelLarge
    .copy(
        fontSize = 12.sp,       // homepage filtreleme etiketleri
        lineHeight = 16.sp
    )

val TitleMediumCardInfo = Typography.titleMedium
    .copy(
        fontSize = 14.sp,       // homepage lokasyon kartlarındaki bilgiler
        lineHeight = 18.sp
    )

val LabelMediumRating = Typography.labelMedium
    .copy(
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,     // homepage kartlarındaki rating bilgisi
        lineHeight = 14.sp
    )

val LabelMediumCardTitle = Typography.labelMedium
    .copy(
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,       // homepage kartlarındaki başlık bilgisi
        lineHeight = 25.sp
    )

val TitleMediumSearchResults = Typography.titleMedium
    .copy(
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,       // homepage arama sonuçlarının bilgisi
        lineHeight = 25.sp
    )

val HeadlineLargePlaceDetailsTitle = Typography.headlineLarge
    .copy(
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,     // detay ekranındaki konum başlığı
        lineHeight = 28.sp
    )

val BodyMediumDetailScreenOverviewDetails = Typography.bodyMedium
    .copy(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,     // detay ekranındaki uzun açıklama yazısı
        lineHeight = 16.sp
    )

// EN SON TYPOGRAPHY AYARLANDI, LINEHEIGHT VERİLDİ.  / DATA OLUŞTURULACAK. 03.09.2025 - 12.40
