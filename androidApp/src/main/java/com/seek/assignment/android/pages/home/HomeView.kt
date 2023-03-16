package com.seek.assignment.android.pages.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.seek.assignment.android.ShowSnackbar
import com.seek.assignment.android.common.utils.ScreenUtils
import com.seek.assignment.android.components.SurfaceMode
import com.seek.assignment.android.components.bars.TopBar
import com.seek.assignment.android.components.list.ListItem
import com.seek.assignment.android.components.loader.Spinner
import com.seek.assignment.android.components.screen.ScreenScaffold
import com.seek.assignment.android.theme.AssignmentAppTheme
import com.seek.assignment.android.theme.toColor
import com.seek.assignment.core.model.StringKey
import com.seek.assignment.core.service.LanguageService
import org.koin.androidx.compose.inject

const val homeViewRoute = "homeViewRoute"

@Composable
fun HomeView(
    navController: NavController,
    showSnackbar: ShowSnackbar,
    onPostingClick: (Int) -> Unit
) {
    val viewModel: HomeViewModel = viewModel()
    val viewState: HomeViewState = viewModel.viewState
    viewModel.processViewModelActions(showSnackbar)
    val languageService: LanguageService by inject()

    ScreenScaffold(
        navController = navController,
        topBar = {
            TopBar(
                title = languageService.getResourceString(StringKey.jobPostings),
                rightButtonImageToken = AssignmentAppTheme.resource.moreIcon,
                rightButtonOnClick = {
                }
            )
        },
        hasBottomNavBar = true
    ) {
        val systemUiController = rememberSystemUiController()
        val statusBarColor = AssignmentAppTheme.colors.backgroundColor.toColor()

        SideEffect {
            ScreenUtils.setStatusBarColor(systemUiController, statusBarColor)
        }

        if (viewState.progress) {
            Spinner(fullscreen = true)
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AssignmentAppTheme.spacing.verticalPadding8.dp)
            ) {
                items(viewState.jobPostings) {
                    ListItem(
                        containerModifier = Modifier.padding(AssignmentAppTheme.spacing.verticalPadding8.dp),
                        headline = it.jobTitle,
                        text = it.companyName,
                        description = it.jobDescription
                    ) {
                        onPostingClick(it.jobId)
                    }
                }
            }
        }
    }
}

private fun HomeViewModelInterface.processViewModelActions(
    showSnackbar: ShowSnackbar
) = with(this) {
    println("HomeView.processViewModelActions $showInfoMessage")
    if (showInfoMessage.needsProcessing) {
        showSnackbar(showInfoMessage.message)
        showInfoMessage = showInfoMessage.copy(needsProcessing = false)
    }
}