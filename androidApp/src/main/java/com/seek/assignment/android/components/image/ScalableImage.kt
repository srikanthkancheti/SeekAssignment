package com.seek.assignment.android.components.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.seek.assignment.android.AssignmentApp
import com.seek.assignment.android.theme.AssignmentAppTheme

@Composable
fun ScalableImage(
    modifier: Modifier = Modifier,
    imageSize: Int = AssignmentAppTheme.size.iconSize24,
    painter: Painter,
    contentDescription: String? = null,
    colorFilter: ColorFilter? = null
) {
    val fontScale = AssignmentApp.instance.resources.configuration.fontScale
    Image(
        modifier = modifier.then(Modifier.size(imageSize.dp.times(fontScale))),
        painter = painter,
        contentDescription = contentDescription,
        colorFilter = colorFilter
    )
}