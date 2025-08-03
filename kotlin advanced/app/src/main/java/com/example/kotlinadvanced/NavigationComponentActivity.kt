package com.example.kotlinadvanced

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController

class NavigationComponentActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_component)

    }

    override fun onSupportNavigateUp(): Boolean {
        navController=findNavController(R.id.navHostFragmentCView)
        return  navController.navigateUp() || super.onSupportNavigateUp()
    }
}