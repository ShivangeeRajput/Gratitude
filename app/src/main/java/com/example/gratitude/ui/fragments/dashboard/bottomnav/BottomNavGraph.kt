package com.example.gratitude.ui.fragments.dashboard.bottomnav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gratitude.ui.fragments.dashboard.screens.HomeScreen
import com.example.gratitude.ui.fragments.dashboard.screens.JournalScreen
import com.example.gratitude.ui.fragments.dashboard.screens.ProfileScreen
import com.example.gratitude.ui.fragments.dashboard.screens.QuotesScreen
import com.example.gratitude.ui.viewmodels.OnboardingSharedViewModel


@Composable
fun BottomNavGraph(
    navController: NavHostController,
    sharedViewModel: OnboardingSharedViewModel
) {
    NavHost(navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            HomeScreen(sharedViewModel = sharedViewModel)
        }
        composable(BottomNavItem.Journal.route) {
            JournalScreen()
        }
        composable(BottomNavItem.Quotes.route) {
            QuotesScreen()
        }
        composable(BottomNavItem.Profile.route) {
            ProfileScreen()
        }
    }
}
