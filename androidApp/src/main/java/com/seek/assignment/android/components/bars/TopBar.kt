package com.seek.assignment.android.components.bars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.seek.assignment.android.common.utils.MultipleEventsCutter
import com.seek.assignment.android.common.utils.get
import com.seek.assignment.android.components.SurfaceMode
import com.seek.assignment.android.components.buttons.ImageButton
import com.seek.assignment.android.components.text.FontScale
import com.seek.assignment.android.components.text.TextView
import com.seek.assignment.android.theme.AssignmentAppTheme
import com.seek.assignment.android.theme.toColor
import com.seek.assignment.branding.AssignmentAppTypography

/**
 * [Figma Design]()
 */

enum class TopBarLargeTitleTextStyle {
    LARGE {
        @Composable
        override fun typography(): AssignmentAppTypography = AssignmentAppTheme.typography.headline1
    },
    MEDIUM {
        @Composable
        override fun typography(): AssignmentAppTypography = AssignmentAppTheme.typography.headline2
    };

    @Composable
    abstract fun typography(): AssignmentAppTypography
}

enum class TopBarTitleTextStyle {
    SCREEN {
        @Composable
        override fun typography(): AssignmentAppTypography = AssignmentAppTheme.typography.headline3

        @Composable
        override fun color(): Long = AssignmentAppTheme.colors.topNavTitleColor
    },
    SHEET {
        @Composable
        override fun typography(): AssignmentAppTypography = AssignmentAppTheme.typography.subtitle1

        @Composable
        override fun color(): Long = AssignmentAppTheme.colors.headlineTitleColor
    };

    @Composable
    abstract fun typography(): AssignmentAppTypography

    @Composable
    abstract fun color(): Long
}

/**
 * Defines scrolling behavior for [TopBar]. Requires adding [Modifier.nestedScroll] with argument
 * [scrollConnection] to content container.
 */
abstract class TopBarScrollBehavior {
    /**
     * Offset from default [TopBar] size, in pixels
     */
    val currentOffsetPx = mutableStateOf(0f)

    /**
     * Maximum allowed height difference for offset, in pixels. Set by [TopBar]
     */
    internal var maximumOffsetPx: Float = 0f

    abstract val scrollConnection: NestedScrollConnection
}

/**
 * Adds collapsing behavior to TopBar that is preserved in saved instance.
 * See [TopBarScrollBehavior]
 */
@Composable
fun rememberTopBarCollapsableBehavior(): TopBarScrollBehavior =
    rememberSaveable(saver = TopBarCollapseSaver) {
        TopBarCollapseBehavior()
    }

private class TopBarCollapseBehavior : TopBarScrollBehavior() {
    override val scrollConnection = object : NestedScrollConnection {
        override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
            val oldOffset = currentOffsetPx.value
            val newOffset = (oldOffset - available.y)
                .coerceAtLeast(0f)
                .coerceAtMost(maximumOffsetPx)
            currentOffsetPx.value = newOffset
            return Offset(0f, oldOffset - newOffset)
        }
    }
}

private object TopBarCollapseSaver : Saver<TopBarCollapseBehavior, FloatArray> {
    override fun restore(value: FloatArray) = TopBarCollapseBehavior().apply {
        maximumOffsetPx = value[0]
        currentOffsetPx.value = value[1]
    }

    override fun SaverScope.save(value: TopBarCollapseBehavior) = floatArrayOf(
        value.maximumOffsetPx,
        value.currentOffsetPx.value
    )
}

@Composable
fun TopBar(
    title: String? = null,
    titleTextStyle: TopBarTitleTextStyle = TopBarTitleTextStyle.SCREEN,
    titleAlign: TextAlign = TextAlign.Start,
    largeTitleTextStyle: TopBarLargeTitleTextStyle = TopBarLargeTitleTextStyle.LARGE,
    hasBackButton: Boolean = false,
    backButtonImageToken: String = AssignmentAppTheme.resource.back,
    backButtonTint: Long = AssignmentAppTheme.colors.topNavIconColor,
    backButtonOnClick: () -> Unit = {},
    hasRightButton: Boolean = true,
    rightButtonImageToken: String? = null,
    rightButtonTint: Long = AssignmentAppTheme.colors.topNavIconColor,
    rightButtonOnClick: () -> Unit = {},
    surfaceMode: SurfaceMode = SurfaceMode.OnBackground,
    scrollBehavior: TopBarScrollBehavior? = null,
    ) {
    val scrollOffsetPx: Float? = scrollBehavior?.currentOffsetPx?.value
    val expandedHeight = AssignmentAppTheme.spacing.verticalSpacing128
    val collapsedHeight = AssignmentAppTheme.spacing.verticalSpacing56
    val topPadding = AssignmentAppTheme.spacing.verticalPadding16
    val bottomPadding = AssignmentAppTheme.spacing.verticalPadding8

    // For expandable TopBar
    val heightDifference = (expandedHeight - collapsedHeight).dp
    scrollBehavior?.maximumOffsetPx = with(LocalDensity.current) { heightDifference.toPx() }
    val scrollOffsetDp = with(LocalDensity.current) { (scrollOffsetPx ?: 0f).toDp() }.value

    val height: Modifier
    val largeText: Boolean
    val smallAlpha: Float
    val largeAlpha: Float
    if (scrollBehavior != null) {
        // Switch on 2/3 of height between titles, with linear fading between 1/3 and 1 ratio
        val ratio = scrollBehavior.currentOffsetPx.value / scrollBehavior.maximumOffsetPx
        largeText = ratio < 0.667f
        smallAlpha = (ratio * 3 - 2).coerceIn(0f, 1f) // 0.667..1 -> 0..1
        largeAlpha = (2f - ratio * 3f).coerceIn(0f, 1f) // 0.333..0.667 -> 1..0
        height = Modifier.height(
            (expandedHeight - scrollOffsetDp)
                .coerceAtLeast(collapsedHeight.toFloat())
                .coerceAtMost(expandedHeight.toFloat())
                .dp
        )
    } else {
        largeText = false
        smallAlpha = 1f
        largeAlpha = 1f
        height = Modifier.height(collapsedHeight.dp)
    }

    val backgroundColor = when (surfaceMode) {
        SurfaceMode.OnSurface -> AssignmentAppTheme.colors.topNavSurfaceColor.toColor()
        else -> AssignmentAppTheme.colors.topNavColor.toColor()
    }

    Box(
        contentAlignment = Alignment.TopStart,
        modifier = height
            .background(color = backgroundColor)
            .fillMaxWidth()
            .padding(
                start = AssignmentAppTheme.spacing.verticalPadding16.dp,
                end = AssignmentAppTheme.spacing.verticalPadding16.dp,
                top = topPadding.dp,
                bottom = bottomPadding.dp
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height((collapsedHeight - topPadding - bottomPadding).dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                BackButton(
                    hasBackButton,
                    backButtonImageToken,
                    backButtonTint,
                    backButtonOnClick
                )
                if (!largeText) {
                    InLineTitle(titleAlign, hasBackButton, title, titleTextStyle, smallAlpha, Modifier.weight(1f))
                }
            }

            RightButton(hasRightButton, rightButtonImageToken, rightButtonTint, rightButtonOnClick, Modifier)
        }
        if (largeText) {
            title?.let {
                TextView(
                    modifier = Modifier
                        .padding(start = AssignmentAppTheme.spacing.horizontalPadding8.dp)
                        .align(Alignment.BottomStart)
                        .alpha(largeAlpha)
                        .fillMaxWidth(),
                    text = it,
                    style = largeTitleTextStyle.typography(),
                    color = AssignmentAppTheme.colors.topNavTitleColor
                )
            }
        }
    }
}

@Composable
private fun BackButton(
    hasBackButton: Boolean,
    imageToken: String = AssignmentAppTheme.resource.back,
    backButtonTint: Long,
    backButtonOnClick: () -> Unit
) {
    if (hasBackButton) {
        ImageButton(
            imageToken = imageToken,
            tint = backButtonTint,
            isScalable = true
        ) {
            backButtonOnClick.invoke()
        }
    } else {
        Spacer(modifier = Modifier.size(1.dp))
    }
}

@Composable
private fun InLineTitle(
    titleAlign: TextAlign,
    hasBackButton: Boolean,
    title: String?,
    titleTextStyle: TopBarTitleTextStyle,
    alpha: Float,
    modifier: Modifier
) {
    val titleStartWithPadding = titleAlign == TextAlign.Start && hasBackButton

    title?.let {
        val textModifier = when {
            titleStartWithPadding -> modifier.padding(start = AssignmentAppTheme.spacing.horizontalPadding32.dp)
            hasBackButton -> modifier.padding(end = AssignmentAppTheme.size.iconSize24.dp)
            else -> modifier
        }

        TextView(
            modifier = textModifier
                .alpha(alpha),
            text = it,
            style = titleTextStyle.typography(),
            color = titleTextStyle.color(),
            textAlign = titleAlign,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            maxFontScale = FontScale.EXTRA_LARGE.value
        )
    }
}

@Composable
private fun RightButton(
    hasRightButton: Boolean,
    rightButtonImageToken: String?,
    rightButtonTint: Long,
    rightButtonOnClick: () -> Unit,
    modifier: Modifier
) {
    if (rightButtonImageToken != null && hasRightButton) {
        val multipleEventsCutter = remember { MultipleEventsCutter.get() }
        Column(horizontalAlignment = Alignment.End, modifier = modifier) {
            ImageButton(
                imageToken = rightButtonImageToken,
                tint = rightButtonTint,
                isScalable = true
            ) {
                multipleEventsCutter.processEvent { rightButtonOnClick.invoke() }
            }
        }
    }
}