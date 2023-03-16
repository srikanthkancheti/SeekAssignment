package com.seek.assignment.android.components.loader

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieDynamicProperty
import com.seek.assignment.android.common.utils.LocalLottieAnimationIterations
import com.seek.assignment.android.theme.AssignmentAppTheme
import com.seek.assignment.android.theme.toAndroidColor
import com.seek.assignment.android.theme.toColor
import com.seek.assignment.android.theme.toRaw

/**
 * [Figma Design]()
 */

enum class ProgressSize {
    SMALL,
    MEDIUM
}

enum class ProgressType {
    PRIMARY,
    SECONDARY,
    TEXT,
    GENERAL
}

@Composable
fun Spinner(
    modifier: Modifier = Modifier,
    fullscreen: Boolean = false,
    size: ProgressSize = ProgressSize.MEDIUM,
    type: ProgressType = ProgressType.GENERAL,
    isInverted: Boolean = true,
    overrideLottieAnimationColor: Boolean = true,
    color: Color = AssignmentAppTheme.colors.iconTintColor.toColor()
) {
    if (fullscreen) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            SpinnerLottie(modifier, type, isInverted, overrideLottieAnimationColor, size, color)
        }
    } else {
        SpinnerLottie(modifier, type, isInverted, overrideLottieAnimationColor, size, color)
    }

}

@Composable
private fun SpinnerLottie(
    modifier: Modifier = Modifier,
    type: ProgressType,
    isInverted: Boolean,
    overrideLottieAnimationColor: Boolean = true,
    size: ProgressSize,
    color: Color
) {
    val resource = when (type) {
        ProgressType.PRIMARY -> if (isInverted) AssignmentAppTheme.resource.lottiePrimaryLoader else AssignmentAppTheme.resource.lottieSecondaryLoader
        ProgressType.SECONDARY -> if (isInverted) AssignmentAppTheme.resource.lottieSecondaryLoader else AssignmentAppTheme.resource.lottiePrimaryLoader
        ProgressType.TEXT -> if (isInverted) AssignmentAppTheme.resource.lottieSecondaryLoader else AssignmentAppTheme.resource.lottiePrimaryLoader
        ProgressType.GENERAL -> AssignmentAppTheme.resource.lottiePrimaryLoader
    }

    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(
            resource.toRaw()
        )
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LocalLottieAnimationIterations.current.iterations
    )

    val dynamicProperties = rememberLottieDynamicProperties(
        rememberLottieDynamicProperty(
            keyPath = arrayOf("**"),
            property = LottieProperty.COLOR_FILTER,
            callback = { PorterDuffColorFilter(color.toAndroidColor(), PorterDuff.Mode.SRC_ATOP) })
    )

    val lottieAnimSize =
        if (size == ProgressSize.MEDIUM) AssignmentAppTheme.size.iconSize32.dp else AssignmentAppTheme.size.iconSize24.dp
    LottieAnimation(
        dynamicProperties = if (overrideLottieAnimationColor) dynamicProperties else rememberLottieDynamicProperties(),
        composition = composition,
        progress = progress,
        modifier = modifier
            .width(lottieAnimSize)
            .height(lottieAnimSize)
    )
}