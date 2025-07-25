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
import com.example.gratitude.databinding.FragmentOnboardingStepJournalBinding
import com.example.gratitude.ui.fragments.onboarding.OnboardingStepsFragment
import com.example.gratitude.ui.viewmodels.OnboardingSharedViewModel
import com.google.android.material.card.MaterialCardView

class OnboardingStepJournalFragment : Fragment() {

    private var _binding: FragmentOnboardingStepJournalBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: OnboardingSharedViewModel by activityViewModels()
    private var selectedCard: MaterialCardView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingStepJournalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sharedViewModel.userName.observe(viewLifecycleOwner) { name ->
            binding.tvQuestion.text = "Hey $name! What’s your current journaling habit?"
        }

        val container = binding.optionContainer

        binding.btnContinue.isEnabled = false
        binding.btnContinue.background = ContextCompat.getDrawable(requireContext(), R.drawable.primary_theme_disabled_btn_background)

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
                sharedViewModel.setJournalingHabit(answer)

                binding.btnContinue.isEnabled = true
                binding.btnContinue.background = ContextCompat.getDrawable(requireContext(), R.drawable.primary_theme_enable_btn_background
                )
            }
        }

        binding.btnContinue.setOnClickListener {
            (requireParentFragment() as? OnboardingStepsFragment)?.goToNextPage()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}








