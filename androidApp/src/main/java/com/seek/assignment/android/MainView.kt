package com.seek.assignment.android

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.seek.assignment.android.theme.AssignmentAppTheme
import com.seek.assignment.android.theme.toColor

@Composable
fun MainView() {
    Column(
        modifier = Modifier
            .background(AssignmentAppTheme.colors.backgroundColor.toColor())
            .fillMaxWidth()
            .fillMaxHeight()
    ){}

    MainNavigation()
}