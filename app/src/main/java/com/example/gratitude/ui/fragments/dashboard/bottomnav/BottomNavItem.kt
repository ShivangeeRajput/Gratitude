package com.example.gratitude.ui.fragments.dashboard.bottomnav

import com.example.gratitude.R



sealed class BottomNavItem(val route: String, val icon: Int, val label: String) {
    object Home : BottomNavItem("home", R.drawable.book, "Home")
    object Journal : BottomNavItem("Affirmations", R.drawable.heart, "Journal")
    object Quotes : BottomNavItem("quotes", R.drawable.quotes, "Quotes")
    object Profile : BottomNavItem("profile", R.drawable.user, "Profile")
}
