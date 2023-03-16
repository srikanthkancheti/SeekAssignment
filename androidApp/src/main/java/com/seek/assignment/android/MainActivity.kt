package com.seek.assignment.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.accompanist.insets.ProvideWindowInsets
import com.seek.assignment.android.theme.AssignmentAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssignmentAppTheme {
                ProvideWindowInsets {
                    MainView()
                }
            }
        }
    }
}
