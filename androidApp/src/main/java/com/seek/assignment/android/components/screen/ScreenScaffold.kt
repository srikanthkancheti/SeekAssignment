package com.seek.assignment.android.components.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavController
import com.google.accompanist.insets.systemBarsPadding
import com.seek.assignment.android.components.SurfaceMode
import com.seek.assignment.android.components.bars.BottomNavBar
import com.seek.assignment.android.components.bars.Snackbar
import com.seek.assignment.android.components.bars.SnackbarMessageType
import com.seek.assignment.android.components.buttons.StickyContainer
import com.seek.assignment.android.components.model.SnackBarData
import com.seek.assignment.android.theme.AssignmentAppTheme
import com.seek.assignment.core.service.LanguageService

@Composable
fun ScreenScaffold(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
    topBar: @Composable () -> Unit = {},
    hasBottomNavBar: Boolean = false,
    hasGradient: Boolean = true,
    stickyContainerContent: @Composable () -> Unit = {},
    showStickyContainer: Boolean = false,
    surfaceMode: SurfaceMode = SurfaceMode.OnBackground,
    stickyContainerSurfaceMode: SurfaceMode = surfaceMode,
    snackBarHostState: SnackbarHostState? = null,
    snackBarOnAction: () -> Unit = { },
    snackBarIsError: Boolean = false,
    snackBarData: SnackBarData? = null,
    onBackButtonPressed: (() -> Boolean)? = null,
    content: @Composable () -> Unit
) {

    val snackBarHostStateInternal = remember { SnackbarHostState() }
    val snackBarIsErrorInternal by remember { mutableStateOf(false) }
    val snackBarOnActionInternal by remember { mutableStateOf({}) }
    val ls = LanguageService()
    val focusManager = LocalFocusManager.current

    BackHandler(enabled = onBackButtonPressed != null) {
        val popBackStack = onBackButtonPressed?.invoke()
        if (popBackStack == true) {
            navController?.popBackStack()
        }
    }

    Scaffold(
        modifier = modifier.systemBarsPadding(top = true).pointerInput(Unit) {
            detectTapGestures(onTap = {
                focusManager.clearFocus()
            })
        },
        topBar = topBar,
        bottomBar = {
            if (hasBottomNavBar && navController != null) {
                BottomNavBar(navController)
            }
        },
    ) { innerPadding ->
        val color = when (surfaceMode) {
            SurfaceMode.OnSurface -> AssignmentAppTheme.colors.surfaceColor
            SurfaceMode.OnBackground -> AssignmentAppTheme.colors.backgroundColor
        }

        Box(modifier = Modifier.fillMaxSize()) {
            ScreenBackground(
                modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()),
                color = color,
                background = color
            ) {
                content()
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = innerPadding.calculateBottomPadding())
            ) {
                if (showStickyContainer) {
                    StickyContainer(
                        modifier = Modifier.align(Alignment.Center),
                        hasGradient = hasGradient,
                        surfaceMode = stickyContainerSurfaceMode
                    ) {
                        stickyContainerContent()
                    }
                }

                snackBarHostState?.let {
                    Snackbar(
                        modifier = Modifier.align(Alignment.BottomCenter),
                        snackbarHostState = it,
                        isError = snackBarIsError,
                        notErrorMessageType = snackBarData?.notErrorMessageType ?: SnackbarMessageType.Check,
                        hasIcon = snackBarData?.snackBarHasIcon ?: false,
                        onAction = snackBarOnAction
                    )
                }

                Snackbar(
                    modifier = Modifier.align(Alignment.TopCenter),
                    snackbarHostState = snackBarHostStateInternal,
                    isError = snackBarIsErrorInternal,
                    hasIcon = snackBarData?.snackBarHasIcon ?: false,
                    notErrorMessageType = snackBarData?.notErrorMessageType ?: SnackbarMessageType.Check,
                    onAction = snackBarOnActionInternal
                )
            }
        }
    }
}
