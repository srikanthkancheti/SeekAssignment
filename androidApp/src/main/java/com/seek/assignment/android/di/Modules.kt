package com.seek.assignment.android.di

import com.seek.assignment.android.pages.home.HomeViewModel
import org.koin.dsl.module

val viewModelsModule = module {
    single { HomeViewModel(get()) }
}