package com.example.gratitude.ui.fragments.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gratitude.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OnboardingStepsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_steps)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.onboarding_steps_framelayout, OnboardingStepsFragment())
                .commit()
        }
    }
}