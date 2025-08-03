package com.example.gratitude.ui.fragments.onboarding.adapter


import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.gratitude.ui.fragments.onboarding.steps.OnboardingStepGratitudeFragment
import com.example.gratitude.ui.fragments.onboarding.steps.OnboardingStepJournalFragment
import com.example.gratitude.ui.fragments.onboarding.steps.OnboardingStepUserInfoFragment
import com.example.gratitude.ui.fragments.onboarding.steps.OnboardingStepFocusAreaFragment
import com.example.gratitude.ui.fragments.onboarding.steps.OnboardingStepSummaryFragment


class OnboardingStepsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnboardingStepUserInfoFragment()
            1 -> OnboardingStepJournalFragment()
            2 -> OnboardingStepGratitudeFragment()
            3 -> OnboardingStepFocusAreaFragment()
            4 -> OnboardingStepSummaryFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}
