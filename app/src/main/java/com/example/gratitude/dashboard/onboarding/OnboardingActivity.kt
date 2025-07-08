package com.example.gratitude.dashboard.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gratitude.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.onboarding_framelayout, OnboardingFragment())
                .commit()
        }
    }
}
