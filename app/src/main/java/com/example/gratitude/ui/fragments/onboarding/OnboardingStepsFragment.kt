package com.example.gratitude.ui.fragments.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.gratitude.R
import com.example.gratitude.databinding.FragmentOnboardingStepsBinding
import com.example.gratitude.ui.fragments.onboarding.adapter.OnboardingStepsPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

class OnboardingStepsFragment : Fragment() {

    private var _binding: FragmentOnboardingStepsBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: OnboardingStepsPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingStepsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = OnboardingStepsPagerAdapter(this)
        binding.onboardingViewPager.adapter = adapter


        binding.onboardingViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateProgress(position)
            }
        })
    }

    fun goToNextPage() {
        val nextItem = binding.onboardingViewPager.currentItem + 1
        if (nextItem < 6) {
            binding.onboardingViewPager.currentItem = nextItem
        }
    }

    private fun updateProgress(position: Int) {
        val steps = listOf(
            binding.progressStep1,
            binding.progressStep2,
            binding.progressStep3,
            binding.progressStep4,
            binding.progressStep5
        )
        steps.forEachIndexed { index, view ->
            view.setBackgroundResource(
                if (index <= position) R.drawable.onboarding_steps_highlighted
                else R.drawable.onboarding_steps
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

