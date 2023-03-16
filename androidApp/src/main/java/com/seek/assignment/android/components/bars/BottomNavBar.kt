package com.seek.assignment.android.components.bars

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.seek.assignment.android.components.text.TextView
import com.seek.assignment.android.pages.home.homeViewRoute
import com.seek.assignment.android.theme.AssignmentAppTheme
import com.seek.assignment.android.theme.toColor
import com.seek.assignment.android.theme.toResource
import com.seek.assignment.core.model.StringKey
import com.seek.assignment.core.service.LanguageService

/**
 * [Figma Design]()
 */

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BottomNavBar(
    navController: NavController, items: List<NavigationItem> = listOf(
        OneAppNavigationItem.Home,
//        OneAppNavigationItem.Tasks,
//        OneAppNavigationItem.Feed,
//        OneAppNavigationItem.Profile
    )
) {
    BottomNavigation(
        modifier = Modifier.semantics {
            testTagsAsResourceId = true
        },
        backgroundColor = AssignmentAppTheme.colors.bottomNavColor.toColor()
    ) {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route

        items.forEach { item ->
            val isSelected = currentRoute == item.route
            val activeItemColor = AssignmentAppTheme.colors.bottomNavActiveTab
            val inactiveItemColor = AssignmentAppTheme.colors.bottomNavInactiveTab

            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(
                            id = if (isSelected) item.iconActive else item.iconInactive
                        ),
                        tint = if (isSelected) activeItemColor.toColor() else inactiveItemColor.toColor(),
                        contentDescription = item.title
                    )
                },
                label = {
                    TextView(
                        text = item.title,
                        style = AssignmentAppTheme.typography.caption2,
                        color = if (isSelected) activeItemColor else inactiveItemColor,
                        isScalable = false
                    )
                },
                selected = isSelected,
                selectedContentColor = activeItemColor.toColor(),
                unselectedContentColor = inactiveItemColor.toColor(),
                alwaysShowLabel = true,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    //BottomNavBar()
}

open class NavigationItem(var route: String,
                          var title: String,
                          var iconActive: Int,
                          val iconInactive: Int)

sealed class OneAppNavigationItem(
    route: String,
    title: String,
    iconActive: Int,
    iconInactive: Int
): NavigationItem(route, title, iconActive, iconInactive) {
    object Home : NavigationItem(
        homeViewRoute,
        LanguageService().getResourceString(StringKey.homeHome),
        AssignmentAppTheme.resource.navbarHomeActive.toResource(),
        AssignmentAppTheme.resource.navbarHomeInactive.toResource()
    )

//    object Tasks : NavigationItem(
//        tasksViewRoute,
//        LanguageService().getResourceString(StringKey.homeTasks),
//        OneAppTheme.resource.navbarTasksActive.toResource(),
//        OneAppTheme.resource.navbarTasksInactive.toResource()
//    )
//
//    object Feed : NavigationItem(
//        feedViewRoute,
//        LanguageService().getResourceString(StringKey.homeFeed),
//        OneAppTheme.resource.navbarFeedActive.toResource(),
//        OneAppTheme.resource.navbarFeedInactive.toResource()
//    )
//
//    object Profile : NavigationItem(
//        profileViewRoute,
//        LanguageService().getResourceString(StringKey.homeProfile),
//        OneAppTheme.resource.navbarProfileActive.toResource(),
//        OneAppTheme.resource.navbarProfileInactive.toResource()
//    )
}