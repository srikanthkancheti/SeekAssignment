package com.seek.assignment.android.components.bars

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.seek.assignment.android.components.image.ScalableImage
import com.seek.assignment.android.components.text.TextView
import com.seek.assignment.android.theme.AssignmentAppTheme
import com.seek.assignment.android.theme.toColor
import com.seek.assignment.android.theme.toResource

/**
 * [Figma Design]()
 */

@Composable
fun Snackbar(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    isError: Boolean,
    notErrorMessageType: SnackbarMessageType = SnackbarMessageType.Check,
    hasIcon: Boolean,
    onAction: () -> Unit = { }
) {
    SnackbarHost(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = AssignmentAppTheme.spacing.horizontalPadding8.dp,
                top = 0.dp,
                end = AssignmentAppTheme.spacing.horizontalPadding8.dp,
                bottom = AssignmentAppTheme.spacing.verticalPadding8.dp
            ),
        hostState = snackbarHostState,
        snackbar = { data ->
            val bgColor = if (isError) AssignmentAppTheme.colors.snackbarFillErrorColor else AssignmentAppTheme.colors.snackbarFillDefaultColor
            val icon = when {
                isError -> AssignmentAppTheme.resource.error
                notErrorMessageType == SnackbarMessageType.Info -> AssignmentAppTheme.resource.info
                else -> AssignmentAppTheme.resource.check
            }
            val fgColor = if (isError) AssignmentAppTheme.colors.snackbarContentErrorColor else AssignmentAppTheme.colors.snackbarContentColor
            val actionColor = if (isError) AssignmentAppTheme.colors.snackbarActionErrorColor else AssignmentAppTheme.colors.snackbarActionColor

            Snackbar(
                backgroundColor = bgColor.toColor(),
                contentColor = fgColor.toColor(),
                shape = RoundedCornerShape(AssignmentAppTheme.cornerRadius.standardRadius.dp),
                content = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        TextView(
                            modifier = Modifier
                                .weight(1f)
                                .padding(
                                    top = AssignmentAppTheme.spacing.verticalPadding12.dp,
                                    bottom = AssignmentAppTheme.spacing.verticalPadding12.dp,
                                ),
                            text = data.message,
                            style = AssignmentAppTheme.typography.body2,
                            color = fgColor,
                        )
                        if (hasIcon) {
                            ScalableImage(
                                painter = painterResource(id = icon.toResource()),
                                colorFilter = ColorFilter.tint(fgColor.toColor())
                            )
                        }
                    }
                },
                action = {
                    data.actionLabel?.let {
                        TextView(
                            modifier = Modifier.padding(end = AssignmentAppTheme.spacing.horizontalPadding8.dp),
                            text = it.uppercase(),
                            style = AssignmentAppTheme.typography.button2,
                            color = actionColor,
                            onClick = onAction
                        )
                    }
                }
            )
        }
    )
}

enum class SnackbarMessageType {
    Check, Info;
}

/**
 * Start interactive mode to see it
 */
@Preview(showBackground = true)
@Composable
private fun SnackbarPreview() {
    AssignmentAppTheme {
        val a = 0
        val snackbarHostState = remember { SnackbarHostState() }

        LaunchedEffect(a) {
            snackbarHostState.showSnackbar("Hello world", duration = SnackbarDuration.Indefinite)
        }

        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.weight(1f))
            Snackbar(
                snackbarHostState = snackbarHostState,
                notErrorMessageType = SnackbarMessageType.Check,
                isError = true,
                hasIcon = true
            )
        }

    }
}