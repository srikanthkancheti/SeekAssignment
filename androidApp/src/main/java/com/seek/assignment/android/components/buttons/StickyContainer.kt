package com.seek.assignment.android.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.seek.assignment.android.components.SurfaceMode
import com.seek.assignment.android.theme.AssignmentAppTheme
import com.seek.assignment.android.theme.toBrush
import com.seek.assignment.android.theme.toColor

/**
 * [Figma Design]()
 */

@Composable
fun StickyContainer(
    modifier: Modifier = Modifier,
    hasGradient : Boolean = true,
    surfaceMode: SurfaceMode = SurfaceMode.OnBackground,
    content: @Composable () -> Unit
) {
    val backgroundColor = when (surfaceMode) {
        SurfaceMode.OnSurface -> AssignmentAppTheme.colors.surfaceColor.toColor()
        else -> AssignmentAppTheme.colors.backgroundColor.toColor()
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.Top)
    ) {

        var gradientBoxModifier = Modifier
            .fillMaxWidth()
            .height(AssignmentAppTheme.size.stackButtonTopBottomHeight.dp)
        if (hasGradient) {
            val gradientBrush = when (surfaceMode) {
                SurfaceMode.OnSurface -> AssignmentAppTheme.gradient.surfaceColorGradient.toBrush()
                else -> AssignmentAppTheme.gradient.backgroundColorGradient.toBrush()
            }
            gradientBoxModifier = gradientBoxModifier.background(brush = gradientBrush)
        }

        Box(modifier = gradientBoxModifier)
        Column(modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .wrapContentHeight(Alignment.Top),
            verticalArrangement = Arrangement.spacedBy(AssignmentAppTheme.spacing.verticalSpacing24.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            content()
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .height(AssignmentAppTheme.size.stackButtonTopBottomHeight.dp))
    }
}