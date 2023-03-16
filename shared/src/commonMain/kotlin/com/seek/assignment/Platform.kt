package com.seek.assignment

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform