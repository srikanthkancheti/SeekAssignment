package com.seek.assignment.branding

import com.seek.assignment.branding.globalTokens.FontWeight

data class AssignmentAppTypography(
    val fontFamily: String, // Use only on Android
    val fontFamilyName: String, // Use only on iOS
    val fontWeight: FontWeight,
    val fontSize: Int,
    val lineHeight: Int,
    val letterSpacing: Int = 0 // TBD and thus for now it is 0.
)

data class AssignmentAppShadow(
    val x: Int,
    val y: Int,
    val blur: Int,
    val spread: Int,
    val color: Long
)

data class AssignmentAppBorder(
    val thickness: Int,
    val type: AssignmentAppBorderType
)

data class AssignmentAppLinearGradient(
    val angle: Int,
    val startColor: Long,
    val endColor: Long
)

enum class AssignmentAppBorderType {
    SOLID,
    DASHED
}
