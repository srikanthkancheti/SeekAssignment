package com.seek.assignment.core

import com.seek.assignment.core.utils.UUID
import kotlinx.coroutines.flow.SharedFlow

sealed class NavigationEvent(
    val id: UUID = UUID.randomUUID(),
) {
    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as NavigationEvent

        if (hashCode() != other.hashCode()) return false

        return true
    }
}

data class NavigateTo(
    val destination: NavigationDestination,
) : NavigationEvent() {
    override fun hashCode(): Int {
        return super.hashCode() + destination.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        if (!super.equals(other)) return false

        other as NavigateTo

        if (hashCode() != other.hashCode()) return false

        return true
    }

}

class NavigateBack : NavigationEvent()

data class NavigateBackTo(val destination: NavigationDestination) : NavigationEvent() {
    override fun hashCode(): Int {
        return super.hashCode() + destination.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        if (!super.equals(other)) return false

        other as NavigateTo

        if (hashCode() != other.hashCode()) return false

        return true
    }
}

object EmptyNavigation : NavigationEvent()

interface NavigationRouter<E : NavigationEvent> {
    val navigationFlow: SharedFlow<E>

    suspend fun emit(event: E)
}