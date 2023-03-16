package com.seek.assignment.android.components.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.seek.assignment.android.common.utils.clickableSingle
import com.seek.assignment.android.components.SurfaceMode
import com.seek.assignment.android.components.text.TextView
import com.seek.assignment.android.theme.AssignmentAppTheme
import com.seek.assignment.android.theme.toColor

@Composable
fun ListItem(
    containerModifier: Modifier = Modifier,
    surfaceMode: SurfaceMode = SurfaceMode.OnSurface,
    isEnabled: Boolean = true,
    isClickable: Boolean = true,
    headline: String,
    text: String,
    description: String,
    onClick: () -> Unit = {}
) {

    val backgroundColor = when (surfaceMode) {
        SurfaceMode.OnBackground -> AssignmentAppTheme.colors.cardBgColor
        SurfaceMode.OnSurface -> AssignmentAppTheme.colors.cardBgColorSurface
    }.toColor()

    val shape = RoundedCornerShape(AssignmentAppTheme.cornerRadius.standardRadius.dp)

    var modifier = containerModifier
    if(isClickable){
        modifier = containerModifier.clickableSingle(
            enabled = isEnabled,
            onClick = {
                onClick()
            }
        )
    }

        Column(
            modifier = modifier
                .fillMaxWidth()
                .clip(shape)
                .background(backgroundColor)
                .padding(
                    start = AssignmentAppTheme.spacing.horizontalPadding8.dp,
                    end = AssignmentAppTheme.spacing.horizontalPadding8.dp,
                    top = AssignmentAppTheme.spacing.verticalPadding16.dp,
                    bottom = AssignmentAppTheme.spacing.verticalPadding16.dp
                )
        ) {
            TextView(
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(if (isEnabled) 1.0f else ContentAlpha.disabled),
                text = headline,
                style = AssignmentAppTheme.typography.subtitle1,
                color = AssignmentAppTheme.colors.cardHeadlineColor,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

            TextView(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = AssignmentAppTheme.spacing.verticalPadding8.dp)
                    .alpha(if (isEnabled) 1.0f else ContentAlpha.disabled),
                text = text,
                style = AssignmentAppTheme.typography.body2,
                color = AssignmentAppTheme.colors.cardTextColor,
                overflow =TextOverflow.Ellipsis,
                maxLines = 1
            )

            TextView(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = AssignmentAppTheme.spacing.verticalPadding16.dp)
                    .alpha(if (isEnabled) 1.0f else ContentAlpha.disabled),
                text = description,
                style = AssignmentAppTheme.typography.body1,
                color = AssignmentAppTheme.colors.listItemTitleColor,
                overflow =TextOverflow.Clip,
                maxLines = 2
            )
        }
}