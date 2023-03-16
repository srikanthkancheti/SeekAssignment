package com.seek.assignment.android.core.navigation

import androidx.navigation.NavController
import com.seek.assignment.core.*

interface NavigationHandler<in Destination : NavigationDestination> :
    DestinationHandler<Destination> {
    fun navigateBack(navController: NavController)
}

class AppNavigationHandler(
    private val handlers: List<DestinationHandler<NavigationDestination>>,
) : NavigationHandler<NavigationDestination> {
    override fun shouldHandle(destination: NavigationDestination): Boolean = true

    fun onEvent(event: NavigationEvent, navController: NavController) {
        println("AppNavigationHandler:: onEvent $event")
        when (event) {
            EmptyNavigation -> Unit
            is NavigateBack -> navigateBack(navController)
            is NavigateBackTo -> navigateBackTo(event.destination, navController)
            is NavigateTo -> navigateTo(event.destination, navController)
        }
    }

    override fun navigateTo(
        destination: NavigationDestination, navController: NavController
    ) {
        println("AppNavigationHandler:: navigateTo $destination")
        handlers.firstOrNull { it.shouldHandle(destination) }
            ?.navigateTo(destination, navController)
    }

    override fun navigateBackTo(destination: NavigationDestination, navController: NavController) {
        println("AppNavigationHandler:: navigateBackTo $destination")
        handlers.firstOrNull { it.shouldHandle(destination) }
            ?.navigateBackTo(destination, navController)
    }

    override fun navigateBack(navController: NavController) {
        println("AppNavigationHandler:: navigateBack")
        navController.popBackStack()
    }

}