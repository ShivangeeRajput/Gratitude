package com.example.gratitude.ui.fragments.welcomepage

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.gratitude.R
import com.example.gratitude.ui.fragments.welcomepage.adapter.WelcomePagerAdapter
import com.example.gratitude.ui.fragments.welcomepage.models.WelcomePage
import com.example.gratitude.databinding.FragmentWelcomePageBinding
import com.example.gratitude.ui.fragments.onboarding.OnboardingStepsActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WelcomePageFragment : Fragment() {

    private var _binding: FragmentWelcomePageBinding? = null
    private val binding get() = _binding!!

    private val pages = listOf(
        WelcomePage("Welcome to Gratitude!", "Feel more like yourself again :)", lottieFile = R.raw.first_page_girl),
        WelcomePage("Track Daily Moments", "Capture the Joy in Your Everyday Life", imageRes = R.drawable.gratitude_icon),
        WelcomePage("Reflect & Grow", "Turn Reflection Into a Path of Healing", imageRes = R.drawable.dreamlife),
        WelcomePage("Celebrate Progress", "Because Every Small Step Matters", imageRes = R.drawable.healed)
    )




    private val autoScrollHandler = Handler(Looper.getMainLooper())
    private lateinit var autoScrollRunnable: Runnable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = WelcomePagerAdapter(pages)
        binding.viewPager.adapter = adapter
        binding.viewPager.offscreenPageLimit = pages.size

        setupDots(pages.size)
        updateDots(0)

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateDots(position)
            }
        })

        autoScrollRunnable = object : Runnable {
            override fun run() {
                val currentItem = binding.viewPager.currentItem
                val nextItem = (currentItem + 1) % pages.size
                binding.viewPager.setCurrentItem(nextItem, true)
                val delay = if (currentItem == 0) 6000L else 3000L
                autoScrollHandler.postDelayed(this, delay)
            }
        }
        autoScrollHandler.postDelayed(autoScrollRunnable, 6000) // 👈 first delay bhi 6 sec


        binding.btnGetStarted.setOnClickListener {
           val intent= Intent(requireContext(), OnboardingStepsActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        binding.existingUser.setOnClickListener {
            Toast.makeText(requireContext(), "Existing User Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupDots(count: Int) {
        binding.dotLayout.removeAllViews()
        repeat(count) { index ->
            val dot = View(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(16, 16).apply {
                    setMargins(8, 0, 8, 0)
                }
                background = ContextCompat.getDrawable(
                    requireContext(),
                    if (index == 0) R.drawable.active_dots else R.drawable.inactive_dots
                )
            }
            binding.dotLayout.addView(dot)
        }
    }

    private fun updateDots(selectedPosition: Int) {
        for (i in 0 until binding.dotLayout.childCount) {
            val dot = binding.dotLayout.getChildAt(i)
            dot.background = ContextCompat.getDrawable(
                requireContext(),
                if (i == selectedPosition) R.drawable.active_dots else R.drawable.inactive_dots
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        autoScrollHandler.removeCallbacks(autoScrollRunnable)
        _binding = null
    }
}

