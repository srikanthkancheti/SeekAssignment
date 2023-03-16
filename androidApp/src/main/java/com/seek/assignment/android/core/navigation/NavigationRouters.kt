package com.seek.assignment.android.core.navigation

import com.seek.assignment.core.NavigationEvent
import com.seek.assignment.core.NavigationRouter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.merge

class NavigationRouters(
    private val routers: List<NavigationRouter<NavigationEvent>>,
) {
    fun navigationFlow(): Flow<NavigationEvent> = routers.map { it.navigationFlow }.merge()
}