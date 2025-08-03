package com.example.gratitude.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class OnboardingSharedViewModel @Inject constructor() : ViewModel() {
    private val _userName = MutableLiveData<String>()
    open val userName: LiveData<String> get() = _userName

    private val _journalingHabit = MutableLiveData<String>()
    val journalingHabit: LiveData<String> get() = _journalingHabit

    private val _gratitudeFrequency = MutableLiveData<String>()
    val gratitudeFrequency: LiveData<String> get() = _gratitudeFrequency

    private val _focusAreas = MutableLiveData<List<String>>()
    val focusAreas: LiveData<List<String>> = _focusAreas

    fun setUserName(name: String) {
        _userName.value = name
    }

    fun setJournalingHabit(habit: String) {
        _journalingHabit.value = habit
    }

    fun setGratitudeFrequency(frequency: String) {
        _gratitudeFrequency.value = frequency
    }

    fun setFocusAreas(selected: List<String>) {
        _focusAreas.value = selected
    }
}
