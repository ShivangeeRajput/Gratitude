package com.example.gratitude.ui.fragments.onboarding.steps

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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

        binding.etName.setOnEditorActionListener { v, actionId, event ->
            val name = binding.etName.text?.toString()?.trim()
            if (!name.isNullOrEmpty() && !hasNavigated) {
                hasNavigated = true
                sharedViewModel.setUserName(name)
                (requireParentFragment() as? OnboardingStepsFragment)?.goToNextPage()
                true
            } else {
                false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


