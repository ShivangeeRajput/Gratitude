package com.example.gratitude.ui.fragments.onboarding.steps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.example.gratitude.R
import com.example.gratitude.databinding.FragmentOnboardingStepGratitudeBinding
import com.example.gratitude.ui.fragments.onboarding.OnboardingStepsFragment
import com.example.gratitude.ui.viewmodels.OnboardingSharedViewModel
import com.google.android.material.card.MaterialCardView


class OnboardingStepGratitudeFragment : Fragment() {

    private var _binding: FragmentOnboardingStepGratitudeBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: OnboardingSharedViewModel by activityViewModels()
    private var selectedCard: MaterialCardView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingStepGratitudeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val container = binding.optionContainer

        for (i in 0 until container.childCount) {
            val card = container.getChildAt(i) as? MaterialCardView ?: continue

            card.setOnClickListener {
                selectedCard?.apply {
                    strokeColor = ContextCompat.getColor(requireContext(), R.color.white)
                    strokeWidth = 1
                }

                card.strokeColor = ContextCompat.getColor(requireContext(), R.color.dark_pink)
                card.strokeWidth = 3
                selectedCard = card

                val selectedTextView = card.getChildAt(0) as? TextView
                val answer = selectedTextView?.text.toString()
                sharedViewModel.setGratitudeFrequency(answer)

                (requireParentFragment() as? OnboardingStepsFragment)?.goToNextPage()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

