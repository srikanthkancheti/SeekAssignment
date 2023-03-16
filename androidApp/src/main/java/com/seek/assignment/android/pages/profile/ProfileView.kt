package com.seek.assignment.android.pages.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.seek.assignment.android.common.utils.ScreenUtils
import com.seek.assignment.android.components.SurfaceMode
import com.seek.assignment.android.components.bars.TopBar
import com.seek.assignment.android.components.bars.rememberTopBarCollapsableBehavior
import com.seek.assignment.android.components.screen.ScreenScaffold
import com.seek.assignment.android.theme.AssignmentAppTheme
import com.seek.assignment.android.theme.toColor
import com.seek.assignment.core.model.StringKey
import com.seek.assignment.core.service.LanguageService
import org.koin.androidx.compose.inject

const val profileViewRoute = "profileViewRoute"

@Composable
fun ProfileView(
    navController: NavController
) {
    val languageService: LanguageService by inject()
    val scrollBehavior = rememberTopBarCollapsableBehavior()

    ScreenScaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.scrollConnection),
        navController = navController,
        topBar = {
            TopBar(
                title = languageService.getResourceString(StringKey.profile),
                scrollBehavior = scrollBehavior
            )
        },
        hasBottomNavBar = true
    ) {

    }
}