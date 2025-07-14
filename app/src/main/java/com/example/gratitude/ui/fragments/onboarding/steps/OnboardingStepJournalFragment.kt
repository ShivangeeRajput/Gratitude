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

        for (i in 0 until container.childCount) {
            val card = container.getChildAt(i) as? MaterialCardView ?: continue

            card.setOnClickListener {
                // Reset previous selection
                selectedCard?.apply {
                    setCardBackgroundColor(ContextCompat.getColor(context, R.color.blue_grey_light))
                    strokeColor = ContextCompat.getColor(context, R.color.white)
                    (getChildAt(0) as? TextView)?.setTextColor(ContextCompat.getColor(context, R.color.black))
                }

                // Set new selection
                card.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.dark_pink))
                card.strokeColor = ContextCompat.getColor(requireContext(), R.color.dark_pink)
                (card.getChildAt(0) as? TextView)?.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

                selectedCard = card
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


