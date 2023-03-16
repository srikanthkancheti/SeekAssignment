package com.seek.assignment.android.components.model

import com.seek.assignment.android.components.bars.SnackbarMessageType

data class SnackBarData(
    val snackBarHasIcon: Boolean = false,
    val notErrorMessageType: SnackbarMessageType = SnackbarMessageType.Check,
)