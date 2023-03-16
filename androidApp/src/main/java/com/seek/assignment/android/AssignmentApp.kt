package com.seek.assignment.android

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.seek.assignment.android.di.viewModelsModule
import com.seek.assignment.core.service.Service
import org.koin.android.ext.koin.androidContext

open class AssignmentApp : Application(), Application.ActivityLifecycleCallbacks {

    override fun onCreate() {
        super.onCreate()
        instance = this
        registerActivityLifecycleCallbacks(this)
        Service.applicationContext = this
        initKoin()
    }

    companion object {
        lateinit var instance: AssignmentApp

        fun isInstanceInitialized() = this::instance.isInitialized
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }

    private fun initKoin() {
        com.seek.assignment.core.di.initKoin {
            androidContext(this@AssignmentApp)
            modules(viewModelsModule)
        }
    }
}