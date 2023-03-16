package com.seek.assignment.android.common.utils

import androidx.compose.runtime.compositionLocalOf
import com.airbnb.lottie.compose.LottieConstants

/**
 * Static that needed for testing ui with lottie animations. If let lottie iterate forever
 * test will always fail because compose is being busy animating it
 * @see <a href="https://github.com/airbnb/lottie-android/issues/1907">Issue in github</a>
 * */
@JvmInline
value class LottieAnimationIterations(val iterations: Int)

val LocalLottieAnimationIterations =
    compositionLocalOf { LottieAnimationIterations(LottieConstants.IterateForever) }
