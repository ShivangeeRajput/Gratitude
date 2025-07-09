package com.example.gratitude.ui.fragments.onboarding.steps

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.gratitude.R
import com.example.gratitude.databinding.FragmentOnboardingStepUserInfoBinding
import com.google.android.material.card.MaterialCardView

class OnboardingStepUserInfoFragment : Fragment() {

    private var _binding: FragmentOnboardingStepUserInfoBinding? = null
    private val binding get() = _binding!!

    private val moodOptions = listOf(
        "Happy",
        "Sad  ",
        "😐 Okay",
        "😵‍💫 Stressed",
        "🙏 Grateful"
    )

    private var selectedCard: MaterialCardView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingStepUserInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupMoodCards()
    }

    private fun setupMoodCards() {
        moodOptions.forEach { moodText ->
            val card = MaterialCardView(requireContext()).apply {
                layoutParams = ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    bottomMargin = 12.dpToPx()
                }
                setCardBackgroundColor(ContextCompat.getColor(context, R.color.black))
                strokeColor = ContextCompat.getColor(context, R.color.white)
                strokeWidth = 1
                radius = 16f
                isClickable = true
                isFocusable = true
                elevation = 0f

                val moodView = TextView(context).apply {
                    text = moodText
                    setTextColor(ContextCompat.getColor(context, R.color.white))
                    textSize = 16f
                    setPadding(16.dpToPx(), 16.dpToPx(), 16.dpToPx(), 16.dpToPx())
                }
                addView(moodView)

                setOnClickListener {
                    selectedCard?.strokeColor = ContextCompat.getColor(context, R.color.white)

                    strokeColor = ContextCompat.getColor(context, R.color.dark_pink)
                    selectedCard = this
                }
            }

            binding.moodOptionContainer.addView(card)
        }
    }

    private fun Int.dpToPx(): Int {
        return (this * Resources.getSystem().displayMetrics.density).toInt()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
