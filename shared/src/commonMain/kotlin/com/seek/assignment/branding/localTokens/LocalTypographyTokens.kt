package com.seek.assignment.branding.localTokens

import com.seek.assignment.branding.AssignmentAppTypography

interface LocalTypographyTokens {
    val display1: AssignmentAppTypography
    val display2: AssignmentAppTypography
    val display3: AssignmentAppTypography
    val headline1: AssignmentAppTypography
    val headline2: AssignmentAppTypography
    val headline3: AssignmentAppTypography
    val headline4: AssignmentAppTypography
    val ingress: AssignmentAppTypography
    val subtitle1: AssignmentAppTypography
    val subtitle2: AssignmentAppTypography
    val body1: AssignmentAppTypography
    val body2: AssignmentAppTypography
    val button1: AssignmentAppTypography
    val button2: AssignmentAppTypography
    val caption1: AssignmentAppTypography
    val caption2: AssignmentAppTypography
    val link1: AssignmentAppTypography
    val link2: AssignmentAppTypography

    fun getBoldVariant(typography: AssignmentAppTypography): AssignmentAppTypography
}
