package com.example.gratitude

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment: Fragment = WelcomeFragment()
        addFragmentToActivity(supportFragmentManager, fragment, R.id.fragment_container_view)
    }

    private fun addFragmentToActivity(fragmentManager: FragmentManager, fragment: Fragment, frameId: Int) {
        fragmentManager.beginTransaction()
            .replace(frameId, fragment)
            .commit()
    }
}