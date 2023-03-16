package com.seek.assignment.android.common.utils

import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.SystemUiController

object ScreenUtils {

    const val heroBarScreenWidthThreshold = 360

    fun setStatusBarColor(systemUiController : SystemUiController, color: Color){
        systemUiController.setStatusBarColor(
            color = color,
            darkIcons = true
        )
    }

    fun closeKeyboard(focusManager: FocusManager){
        focusManager.clearFocus()
    }
}