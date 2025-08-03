package com.example.gratitude.ui.fragments.onboarding.steps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.gratitude.R
import com.example.gratitude.databinding.FragmentOnboardingStepFocusAreaBinding
import com.example.gratitude.ui.fragments.onboarding.OnboardingStepsFragment
import com.example.gratitude.ui.fragments.onboarding.adapter.OnboardingStepFocusAreaAdapter
import com.example.gratitude.ui.fragments.onboarding.steps.models.FocusArea
import com.example.gratitude.ui.viewmodels.OnboardingSharedViewModel
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent


class OnboardingStepFocusAreaFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingStepFocusAreaBinding
    private lateinit var focusAreaAdapter: OnboardingStepFocusAreaAdapter

    private val viewModel: OnboardingSharedViewModel by activityViewModels()

    private val focusAreas = listOf(
        FocusArea("Self Love", R.drawable.self_lovee),
        FocusArea("Health", R.drawable.health),
        FocusArea("Little Things", R.drawable.lil_things),
        FocusArea("Sleep", R.drawable.sleep),
        FocusArea("Productivity", R.drawable.productivity),
        FocusArea("Mindfulness", R.drawable.mindfullness),
        FocusArea("Happiness", R.drawable.happiness),
        FocusArea("Fitness", R.drawable.fitness),
        FocusArea("Peace", R.drawable.love),
        FocusArea("Confidence", R.drawable.confidence)
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
            binding.btnContinue.isEnabled = selectedList.size >= 2
        }

        binding.rvFocusAreas.apply {
            layoutManager = FlexboxLayoutManager(requireContext()).apply {
                flexWrap = FlexWrap.WRAP
                justifyContent = JustifyContent.CENTER
            }
            adapter = focusAreaAdapter
        }
    }

    private fun setupContinueButton() {
        binding.btnContinue.isEnabled = false

        binding.btnContinue.setOnClickListener {
            val selectedItems = focusAreaAdapter.getSelectedItems()
            val selectedTitles = selectedItems.map { it.title }

            viewModel.setFocusAreas(selectedTitles)
            (requireParentFragment() as? OnboardingStepsFragment)?.goToNextPage()
        }
    }
}

