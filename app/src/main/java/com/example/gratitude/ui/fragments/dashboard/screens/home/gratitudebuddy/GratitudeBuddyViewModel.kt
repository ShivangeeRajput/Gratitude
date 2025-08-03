package com.example.gratitude.ui.fragments.dashboard.screens.home.gratitudebuddy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GratitudeBuddyViewModel @Inject constructor(
    private val assistantRepository: GratitudeBuddyRepository
) : ViewModel() {

    private val _reply = MutableLiveData<String?>()
    val reply: LiveData<String?> get() = _reply

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun sendMessage(userMessage: String) {
        viewModelScope.launch {
            _loading.value = true
            _reply.value = null

            assistantRepository.getSelfCareReply(userMessage)
                .onSuccess { response ->
                    _reply.value = response
                }
                .onFailure { e ->
                    _error.value = e.message
                }

            _loading.value = false
        }
    }
}

