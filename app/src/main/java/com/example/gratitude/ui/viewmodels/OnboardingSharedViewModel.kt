package com.example.gratitude.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class OnboardingSharedViewModel : ViewModel() {
    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> get() = _userName

    private val _journalingHabit = MutableLiveData<String>()
    val journalingHabit: LiveData<String> get() = _journalingHabit

    private val _gratitudeFrequency = MutableLiveData<String>()
    val gratitudeFrequency: LiveData<String> get() = _gratitudeFrequency

    fun setUserName(name: String) {
        _userName.value = name
    }

    fun setJournalingHabit(habit: String) {
        _journalingHabit.value = habit
    }

    fun setGratitudeFrequency(frequency: String) {
        _gratitudeFrequency.value = frequency
    }
}
