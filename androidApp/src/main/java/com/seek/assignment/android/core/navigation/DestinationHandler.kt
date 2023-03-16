package com.seek.assignment.android.core.navigation

import androidx.navigation.NavController
import com.seek.assignment.core.NavigationDestination

/**
 * An interface that is responsible for handling specific NavigationDestination type,
 * this can be used to break down navigation logic by delegating destinations
 * to their respective handlers
 */
interface DestinationHandler<in Destination : NavigationDestination> {
    fun shouldHandle(destination: NavigationDestination): Boolean

    fun navigateTo(destination: Destination, navController: NavController)

    fun navigateBackTo(destination: Destination, navController: NavController)
}