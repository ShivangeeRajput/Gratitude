package com.example.gratitude.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class FakeOnboardingSharedViewModel : OnboardingSharedViewModel() {
    private val _userName = MutableLiveData("Shivangee 💫")
    override val userName: LiveData<String> = _userName
}
