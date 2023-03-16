package com.seek.assignment.android.components.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.seek.assignment.android.theme.toColor

@Composable
fun ScreenBackground(
    modifier: Modifier = Modifier,
    color: Long,
    background: Long,
    content: @Composable () -> Unit
) {
    Surface(
        color = color.toColor(),
        modifier = modifier.fillMaxSize().background(background.toColor()),
        content = content,
    )
}