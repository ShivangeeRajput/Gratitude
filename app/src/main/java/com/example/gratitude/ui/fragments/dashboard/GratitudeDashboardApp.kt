package com.example.gratitude.ui.fragments.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.gratitude.ui.fragments.dashboard.bottomnav.BottomNavGraph
import com.example.gratitude.ui.fragments.dashboard.bottomnav.BottomNavigationBar
import com.example.gratitude.ui.viewmodels.OnboardingSharedViewModel


@Composable
fun GratitudeDashboardApp() {
    val navController = rememberNavController()
    val sharedViewModel: OnboardingSharedViewModel = hiltViewModel()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        Box(modifier = Modifier.padding(it)) {
            BottomNavGraph(
                navController = navController,
                sharedViewModel = sharedViewModel
            )
        }
    }
}

