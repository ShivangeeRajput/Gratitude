package com.example.gratitude.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.gratitude.R
import com.example.gratitude.ui.fragments.welcomepage.WelcomePageFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment: Fragment = WelcomePageFragment()
        addFragmentToActivity(supportFragmentManager, fragment, R.id.fragment_container_view)
    }

    private fun addFragmentToActivity(fragmentManager: FragmentManager, fragment: Fragment, frameId: Int) {
        fragmentManager.beginTransaction()
            .replace(frameId, fragment)
            .commit()
    }
}