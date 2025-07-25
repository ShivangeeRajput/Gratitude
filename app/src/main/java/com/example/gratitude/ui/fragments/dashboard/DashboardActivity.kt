package com.example.gratitude.ui.fragments.dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.gratitude.ui.theme.GratitudeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GratitudeTheme {
                GratitudeDashboardApp()
            }
        }

    }
}
