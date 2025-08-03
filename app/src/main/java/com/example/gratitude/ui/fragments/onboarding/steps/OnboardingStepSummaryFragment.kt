package com.example.gratitude.ui.fragments.onboarding.steps

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.gratitude.R
import com.example.gratitude.databinding.FragmentOnboardingStepSummaryBinding
import com.example.gratitude.ui.fragments.dashboard.DashboardActivity
import com.example.gratitude.ui.viewmodels.OnboardingSharedViewModel
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingStepSummaryFragment : Fragment() {

    private var _binding: FragmentOnboardingStepSummaryBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: OnboardingSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingStepSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sharedViewModel.userName.observe(viewLifecycleOwner) { name ->
            binding.tvHeading.text = "Great choice, $name!"
        }

        sharedViewModel.focusAreas.observe(viewLifecycleOwner) { focusList ->
            binding.chipContainer.removeAllViews()

            focusList.forEach { title ->
                val chip = Chip(requireContext()).apply {
                    text = mapTitleToEmoji(title)
                    isClickable = false
                    isCheckable = false
                    chipBackgroundColor = ContextCompat.getColorStateList(requireContext(), R.color.white)
                    chipStrokeColor = ContextCompat.getColorStateList(requireContext(), R.color.dark_pink)
                    chipStrokeWidth = 2f
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                    val params = ViewGroup.MarginLayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    params.setMargins(12, 12, 12, 12)
                    layoutParams = params
                }
                binding.chipContainer.addView(chip)
            }

            binding.lottieView.apply {
                setAnimation(R.raw.happy)
                visibility = View.VISIBLE
                playAnimation()
                loop(true)
            }
        }

        binding.btnDone.setOnClickListener {
            startActivity(Intent(requireActivity(), DashboardActivity::class.java))
            requireActivity().finishAffinity()
        }

    }




    private fun mapTitleToEmoji(title: String): String {
        return when (title) {
            "Self Love" -> "Self 🙂"
            "Health" -> "Health 🏥"
            "Little Things" -> "Little Things 🌸"
            "Sleep" -> "Sleep 😴"
            "Productivity" -> "Productivity 📈"
            "Mindfulness" -> "Mindfulness 🧘"
            "Happiness" -> "Happiness 😊"
            "Fitness" -> "Fitness 💪"
            "Peace" -> "Peace ✌️"
            "Confidence" -> "Confidence 💃"
            else -> title
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
