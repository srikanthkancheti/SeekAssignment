package com.seek.assignment.android.components

enum class SurfaceMode {
    OnBackground, OnSurface;

    operator fun not(): SurfaceMode = if (this == OnSurface) OnBackground else OnSurface
}