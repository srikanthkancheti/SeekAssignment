package com.seek.assignment.android.theme

import androidx.annotation.DrawableRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import androidx.core.graphics.alpha
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import com.seek.assignment.android.AssignmentApp
import com.seek.assignment.android.BuildConfig
import com.seek.assignment.android.R
import com.seek.assignment.branding.AssignmentAppLinearGradient
import com.seek.assignment.branding.AssignmentAppTypography
import com.seek.assignment.branding.seek.AssignmentAppResourcesSeek
import com.seek.assignment.branding.seek.*
import com.seek.assignment.branding.globalTokens.FontWeight
import com.seek.assignment.branding.localTokens.*

private val localColorTokensAmbient = compositionLocalOf<LocalColorTokens> {
    error("No OneApp Colors provided")
}

object AssignmentAppTheme {

    val colors: LocalColorTokens
        @Composable
        @ReadOnlyComposable
        get() = localColorTokensAmbient.current

    val assignmentAppDarkColorPalette: LocalColorTokens
    val assignmentAppLightColorPalette: LocalColorTokens
    val cornerRadius: LocalCornerRadiusTokens
    val size: LocalSizeTokens
    val spacing: LocalSpacingTokens
    val typography: LocalTypographyTokens
    val resource: LocalResourceTokens
    val border: LocalBorderTokens
    val gradient: LocalGradientTokens
    val shadow: LocalShadowTokens

    init {
        assignmentAppDarkColorPalette = AssignmentAppDarkColorPaletteSeek
        assignmentAppLightColorPalette = AssignmentAppLightColorPaletteSeek
            cornerRadius = AssignmentAppCornerRadiusSeek
            size = AssignmentAppSizeSeek
            spacing = AssignmentAppSpacingSeek
            typography = AssignmentAppTypographySeek
            resource = AssignmentAppResourcesSeek
            border = AssignmentAppBorderSeek
            gradient = AssignmentAppGradientSeek
            shadow = AssignmentAppShadowAssignment
    }
}

@Composable
fun AssignmentAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val oneAppColors = if (darkTheme) AssignmentAppTheme.assignmentAppDarkColorPalette else AssignmentAppTheme.assignmentAppLightColorPalette
    CompositionLocalProvider(localColorTokensAmbient provides oneAppColors) {
        MaterialTheme(content = content)
    }
}

fun Long.toColor() = try {
    Color(this)
} catch (e: Exception) {
    Color.Cyan
}

fun Color.toAndroidColor() = android.graphics.Color.argb(
    this.toArgb().alpha,
    this.toArgb().red,
    this.toArgb().green,
    this.toArgb().blue
)

fun String.toResource(@DrawableRes drawableForPreview: Int = R.drawable.error) = try {
    if (isNotInComposePreview()) {
        AssignmentApp.instance.resources.getIdentifier(this, "drawable", AssignmentApp.instance.packageName)
    } else {
        drawableForPreview
    }
} catch (e: Exception) {
    R.drawable.error
}

fun String.toRaw() = AssignmentApp.instance.resources.getIdentifier(this, "raw", AssignmentApp.instance.packageName)

fun isNotInComposePreview(): Boolean {
    return try {
        val isProductionBuild = !BuildConfig.DEBUG
        val notInProductionBuildAndInstanceIsInit = BuildConfig.DEBUG && AssignmentApp.isInstanceInitialized()
        notInProductionBuildAndInstanceIsInit || isProductionBuild
    } catch (e: NoClassDefFoundError) {
        false
    }
}

fun AssignmentAppTypography.toTextStyle(
    typeFaceForPreview: android.graphics.Typeface = android.graphics.Typeface.SANS_SERIF,
    isScalable: Boolean = true,
    maxFontScale: Float? = null
): TextStyle {
    val fontFamily = if (AssignmentApp.isInstanceInitialized()) {
        val resourceId = AssignmentApp.instance.resources.getIdentifier(
            this.fontFamily,
            "font",
            AssignmentApp.instance.packageName
        )
        FontFamily(Font(resourceId))
    } else {
        FontFamily(typeFaceForPreview)
    }

    val systemFontScale = AssignmentApp.instance.resources.configuration.fontScale

    /* Logic to determine whether to set the font size according to font scale set in setting (systemFontScale),
    or font scale (maxFontScale) pass into this extension if any */
    val fontScale = if (maxFontScale != null && systemFontScale > maxFontScale) {
        maxFontScale / systemFontScale
    } else {
        1.0f
    }

    return TextStyle(
        fontFamily = fontFamily,
        fontSize = when {
            isScalable && fontScale == 1.0f -> this.fontSize.sp
            isScalable && fontScale != 1.0f -> this.fontSize.sp.times(fontScale)
            else -> this.fontSize.sp.div(systemFontScale)
        },
        fontWeight = when (this.fontWeight) {
            FontWeight.W100 -> androidx.compose.ui.text.font.FontWeight.W100
            FontWeight.W200 -> androidx.compose.ui.text.font.FontWeight.W200
            FontWeight.W300 -> androidx.compose.ui.text.font.FontWeight.W300
            FontWeight.W400 -> androidx.compose.ui.text.font.FontWeight.W400
            FontWeight.W500 -> androidx.compose.ui.text.font.FontWeight.W500
            FontWeight.W600 -> androidx.compose.ui.text.font.FontWeight.W600
            FontWeight.W700 -> androidx.compose.ui.text.font.FontWeight.W700
            FontWeight.W800 -> androidx.compose.ui.text.font.FontWeight.W800
            FontWeight.W900 -> androidx.compose.ui.text.font.FontWeight.W900
            else -> androidx.compose.ui.text.font.FontWeight.W500
        },
        lineHeight = when {
            isScalable && fontScale == 1.0f -> this.lineHeight.sp
            isScalable && fontScale != 1.0f -> this.lineHeight.sp.times(fontScale)
            else -> this.lineHeight.sp.div(systemFontScale)
        },
        letterSpacing = when {
            isScalable && fontScale == 1.0f -> this.letterSpacing.sp
            isScalable && fontScale != 1.0f -> this.letterSpacing.sp.times(fontScale)
            else -> this.letterSpacing.sp.div(systemFontScale)
        }
    )
}

fun AssignmentAppTypography.toSpanStyle(color: Long, underline: Boolean=false): SpanStyle {
    val resourceId = AssignmentApp.instance.resources.getIdentifier(this.fontFamily, "font", AssignmentApp.instance.packageName)
    return SpanStyle(
        color = color.toColor(),
        fontFamily = FontFamily(Font(resourceId)),
        fontSize = this.fontSize.sp,
        fontWeight = when (this.fontWeight) {
            FontWeight.W100 -> androidx.compose.ui.text.font.FontWeight.W100
            FontWeight.W200 -> androidx.compose.ui.text.font.FontWeight.W200
            FontWeight.W300 -> androidx.compose.ui.text.font.FontWeight.W300
            FontWeight.W400 -> androidx.compose.ui.text.font.FontWeight.W400
            FontWeight.W500 -> androidx.compose.ui.text.font.FontWeight.W500
            FontWeight.W600 -> androidx.compose.ui.text.font.FontWeight.W600
            FontWeight.W700 -> androidx.compose.ui.text.font.FontWeight.W700
            FontWeight.W800 -> androidx.compose.ui.text.font.FontWeight.W800
            FontWeight.W900 -> androidx.compose.ui.text.font.FontWeight.W900
            else -> androidx.compose.ui.text.font.FontWeight.W500
        },
        letterSpacing = this.letterSpacing.sp,
        textDecoration = if(underline) TextDecoration.Underline else TextDecoration.None
    )
}

fun AssignmentAppTypography.toParagraphStyle(): ParagraphStyle {
    return ParagraphStyle(lineHeight = this.lineHeight.sp)
}

fun AssignmentAppLinearGradient.toBrush(): Brush {
    return when (this.angle) {
        90 -> Brush.horizontalGradient(
            colors = listOf(
                this.startColor.toColor(),
                this.endColor.toColor()
            )
        )
        180 -> Brush.verticalGradient(
            colors = listOf(
                this.startColor.toColor(),
                this.endColor.toColor()
            )
        )
        270 -> Brush.horizontalGradient(
            colors = listOf(
                this.startColor.toColor(),
                this.endColor.toColor()
            )
        )
        else -> Brush.verticalGradient(
            colors = listOf(
                this.startColor.toColor(),
                this.endColor.toColor()
            )
        )
    }
}