package com.seek.assignment.android.pages.applications

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.seek.assignment.android.components.SurfaceMode
import com.seek.assignment.android.components.bars.TopBar
import com.seek.assignment.android.components.screen.ScreenScaffold
import com.seek.assignment.android.theme.AssignmentAppTheme
import com.seek.assignment.core.model.StringKey
import com.seek.assignment.core.service.LanguageService
import org.koin.androidx.compose.inject

const val myApplicationsRoute = "myApplicationsRoute"

@Composable
fun MyApplicationsView(
    navController: NavController
) {
    val languageService: LanguageService by inject()
    ScreenScaffold(
        navController = navController,
        topBar = {
            TopBar(
                title = languageService.getResourceString(StringKey.myApplications),
            )
        },
        hasBottomNavBar = true
    ) {

    }
}