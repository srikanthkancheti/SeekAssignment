package com.seek.assignment.core

interface NavigationDestination {
    val link: String

    val hashString: String get() = link.hashCode().toString()
}