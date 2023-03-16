package com.seek.assignment.core.service

import android.content.Context
import androidx.core.app.ComponentActivity
import com.seek.assignment.core.service.Service.activityRef
import com.seek.assignment.core.service.Service.applicationContext
import java.lang.ref.WeakReference

/**
 * Singleton for globally shared instances
 * @property applicationContext necessary to share the application context with the shared services
 * @property activityRef necessary to use shouldShowRequestPermissionRationale from shared services
 */
object Service {
    var applicationContext: Context? = null
    var activityRef: WeakReference<ComponentActivity?>? = null
}