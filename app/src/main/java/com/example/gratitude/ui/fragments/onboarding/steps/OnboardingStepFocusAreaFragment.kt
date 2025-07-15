package com.example.gratitude.ui.fragments.onboarding.steps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gratitude.R
import com.example.gratitude.databinding.FragmentOnboardingStepFocusAreaBinding
import com.example.gratitude.ui.fragments.onboarding.adapter.OnboardingStepFocusAreaAdapter
import com.example.gratitude.ui.fragments.onboarding.steps.models.FocusArea
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class OnboardingStepFocusAreaFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingStepFocusAreaBinding
    private lateinit var focusAreaAdapter: OnboardingStepFocusAreaAdapter
    private val focusAreas = listOf(
        FocusArea("Self Love", R.drawable.self_love),
        FocusArea("Health", R.drawable.gratitude_icon),
        FocusArea("Little Things", R.drawable.tulips),
        FocusArea("Sleep", R.drawable.self_love),
        FocusArea("Productivity", R.drawable.welcome_girl),
        FocusArea("Mindfulness", R.drawable.dreamlife),
        FocusArea("Happiness", R.drawable.happy),
        FocusArea("Fitness", R.drawable.ic_meditate),
        FocusArea("Peace", R.drawable.partner),
        FocusArea("Confidence", R.drawable.tulips)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingStepFocusAreaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        setupContinueButton()
    }

    private fun setupRecyclerView() {
        focusAreaAdapter = OnboardingStepFocusAreaAdapter(focusAreas) { selectedList ->
            // Enable button when 2 or more selected
            binding.btnContinue.isEnabled = selectedList.size >= 2
        }

        binding.rvFocusAreas.apply {
            layoutManager = FlexboxLayoutManager(requireContext()).apply {
                flexWrap = FlexWrap.WRAP
                justifyContent = JustifyContent.FLEX_START
            }
            adapter = focusAreaAdapter
        }
    }

    private fun setupContinueButton() {
        binding.btnContinue.isEnabled = false
        binding.btnContinue.setOnClickListener {
            val selectedItems = focusAreaAdapter.getSelectedItems()

        }
    }
}
