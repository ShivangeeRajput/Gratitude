package com.example.gratitude.ui.fragments.dashboard.bottomnav

import com.example.gratitude.R



sealed class BottomNavItem(val route: String, val icon: Int, val label: String) {
    object Home : BottomNavItem("home", R.drawable.book, "Home")
    object Journal : BottomNavItem("journal", R.drawable.heart, "Journal")
    object Quotes : BottomNavItem("quotes", R.drawable.quotes, "Quotes")
    object Affirmations : BottomNavItem("affirmations", R.drawable.meditation, "Affirmations")
}
