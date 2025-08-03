package com.example.gratitude.ui.fragments.welcomepage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gratitude.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WelcomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_page)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.onboarding_framelayout, WelcomePageFragment())
                .commit()
        }
    }
}
