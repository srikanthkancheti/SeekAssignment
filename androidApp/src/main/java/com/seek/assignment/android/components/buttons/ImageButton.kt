package com.seek.assignment.android.components.buttons

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.seek.assignment.android.AssignmentApp
import com.seek.assignment.android.theme.AssignmentAppTheme
import com.seek.assignment.android.theme.toColor
import com.seek.assignment.android.theme.toResource

/**
 * [Figma Design]()
 */

@Composable
fun ImageButton(
    modifier: Modifier = Modifier,
    imageToken: String,
    enabled: Boolean = true,
    tint: Long?,
    isScalable: Boolean = false,
    onClick: () -> Unit,
) {
    val fontScale = AssignmentApp.instance.resources.configuration.fontScale

    IconButton(
        onClick = onClick,
        modifier = modifier.then(Modifier
            .size(if (isScalable) AssignmentAppTheme.size.iconSize24.dp.times(fontScale) else AssignmentAppTheme.size.iconSize24.dp)),
        enabled = enabled
    ) {
        Icon(
            modifier = Modifier
                .size(if (isScalable) AssignmentAppTheme.size.iconSize24.dp.times(fontScale) else AssignmentAppTheme.size.iconSize24.dp),
            painter = painterResource(id = imageToken.toResource()),
            contentDescription = null,
            tint = tint?.toColor() ?: LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
        )
    }
}
