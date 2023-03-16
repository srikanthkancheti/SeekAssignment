package com.seek.assignment.android

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.seek.assignment.android.components.bars.Snackbar
import com.seek.assignment.android.core.LaunchAppNavigation
import com.seek.assignment.android.pages.applications.MyApplicationsView
import com.seek.assignment.android.pages.applications.myApplicationsRoute
import com.seek.assignment.android.pages.home.HomeView
import com.seek.assignment.android.pages.home.homeViewRoute
import com.seek.assignment.android.pages.profile.ProfileView
import com.seek.assignment.android.pages.profile.profileViewRoute
import com.seek.assignment.android.theme.AssignmentAppTheme
import com.seek.assignment.android.theme.toColor
import com.seek.assignment.core.service.LanguageService
import kotlinx.coroutines.launch

typealias ShowSnackbar = (String) -> Unit

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavigation() {
    val navController = rememberAnimatedNavController()
    val startingDestination = homeViewRoute
    val languageService = LanguageService()
    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarHostStateError = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

//    LaunchAppNavigation(navController)

    fun showSnackbar(message: String) {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(
                message = message, duration = SnackbarDuration.Short
            )
        }
    }

    AnimatedNavHost(
        navController,
        startDestination = startingDestination,
        modifier = Modifier.background(AssignmentAppTheme.colors.backgroundColor.toColor())
    ) {
        composable(homeViewRoute) {
            HomeView(
                navController = navController,
                showSnackbar = ::showSnackbar,
                onPostingClick = {

            })
        }

        composable(myApplicationsRoute) {
            MyApplicationsView(navController = navController)
        }

        composable(profileViewRoute) {
            ProfileView(navController = navController)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Snackbar(modifier = Modifier.align(Alignment.BottomCenter),
            snackbarHostState = snackbarHostState,
            isError = false,
            hasIcon = true,
            onAction = { snackbarHostState.currentSnackbarData?.dismiss() })

        Snackbar(
            modifier = Modifier.align(Alignment.BottomCenter),
            snackbarHostState = snackbarHostStateError,
            isError = true,
            hasIcon = true,
        )
    }
}

