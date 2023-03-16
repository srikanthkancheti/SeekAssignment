package com.seek.assignment.android.core

import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.seek.assignment.android.core.navigation.AppNavigationHandler
import com.seek.assignment.android.core.navigation.NavigationRouters
import com.seek.assignment.core.EmptyNavigation
import org.koin.androidx.compose.get

@Composable
fun LaunchAppNavigation(
    navController: NavController,
    router: NavigationRouters = get(),
    navigationHandler: AppNavigationHandler = get(),
) {
    val navigationEvent by router.navigationFlow().collectAsState(initial = EmptyNavigation)
    val currentNavController by rememberUpdatedState(navController)
    LaunchedEffect(navigationEvent) {
        navigationHandler.onEvent(navigationEvent, currentNavController)
    }
}