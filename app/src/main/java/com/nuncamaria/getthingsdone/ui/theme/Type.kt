package com.nuncamaria.getthingsdone.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nuncamaria.getthingsdone.R

private val Kyn = FontFamily(
    Font(resId = R.font.kyn_bold, weight = FontWeight.Bold),
    Font(resId = R.font.kyn_semibold, weight = FontWeight.SemiBold),
    Font(resId = R.font.kyn_medium, weight = FontWeight.Medium),
    Font(resId = R.font.kyn_regular, weight = FontWeight.Normal)
)

// Set of Material typography styles to start with
val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = Kyn,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 32.sp,
        fontSize = 40.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = Kyn,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 28.sp,
        fontSize = 32.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = Kyn,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 24.sp,
        fontSize = 28.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Kyn,
        fontWeight = FontWeight.Normal,
        lineHeight = 28.sp,
        fontSize = 18.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Kyn,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Kyn,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Kyn,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)