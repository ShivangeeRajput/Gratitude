package com.example.gratitude.ui.fragments.onboarding.steps

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.gratitude.R
import com.example.gratitude.databinding.FragmentOnboardingStepUserInfoBinding
import com.example.gratitude.ui.fragments.onboarding.OnboardingStepsFragment
import com.example.gratitude.ui.viewmodels.OnboardingSharedViewModel

class OnboardingStepUserInfoFragment : Fragment() {

    private var _binding: FragmentOnboardingStepUserInfoBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: OnboardingSharedViewModel by activityViewModels()
    private var hasNavigated = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingStepUserInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnContinue.isEnabled = false
        binding.btnContinue.setBackgroundResource(R.drawable.primary_theme_disabled_btn_background)
        binding.lottieView.apply {
            setAnimation(R.raw.happy)
            visibility = View.VISIBLE
            playAnimation()
            loop(true)
        }

        binding.etName.addTextChangedListener(object : android.text.TextWatcher {
            override fun afterTextChanged(s: android.text.Editable?) {
                val name = s?.toString()?.trim()
                binding.btnContinue.isEnabled = !name.isNullOrEmpty()

                if (!name.isNullOrEmpty()) {
                    binding.btnContinue.setBackgroundResource(R.drawable.primary_theme_enable_btn_background)
                } else {
                    binding.btnContinue.setBackgroundResource(R.drawable.primary_theme_disabled_btn_background)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.etName.setOnEditorActionListener { _, _, _ ->
            tryProceed()
        }

        binding.btnContinue.setOnClickListener {
            tryProceed()
        }
    }

    private fun tryProceed(): Boolean {
        val name = binding.etName.text?.toString()?.trim()
        return if (!name.isNullOrEmpty() && !hasNavigated) {
            hasNavigated = true
            sharedViewModel.setUserName(name)
            (requireParentFragment() as? OnboardingStepsFragment)?.goToNextPage()
            true
        } else {
            false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



