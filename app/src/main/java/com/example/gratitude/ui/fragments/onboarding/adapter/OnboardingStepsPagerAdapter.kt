package com.example.gratitude.ui.fragments.onboarding.adapter


import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.gratitude.ui.fragments.onboarding.steps.OnboardingStepGratitudeFragment
import com.example.gratitude.ui.fragments.onboarding.steps.OnboardingStepJournalFragment
import com.example.gratitude.ui.fragments.onboarding.steps.OnboardingStepUserInfoFragment
import com.example.gratitude.ui.fragments.onboarding.steps.OnboardingStepVisulaizeFragment


class OnboardingStepsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnboardingStepUserInfoFragment()
            1 -> OnboardingStepJournalFragment()
            2 -> OnboardingStepGratitudeFragment()
            3 -> OnboardingStepVisulaizeFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}
