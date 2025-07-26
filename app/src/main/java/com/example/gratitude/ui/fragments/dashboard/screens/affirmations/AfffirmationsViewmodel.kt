package com.example.gratitude.ui.fragments.dashboard.screens.affirmations

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gratitude.ui.fragments.dashboard.screens.affirmations.model.Affirmation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AffirmationViewModel @Inject constructor(
    private val repository: AffirmationsRepository
) : ViewModel() {

    private val _affirmations = mutableStateListOf<Affirmation>()
    val affirmations: List<Affirmation> get() = _affirmations

    private val likedSet = mutableStateListOf<String>()

    fun fetchAffirmations() {
        viewModelScope.launch {
            repository.getAllAffirmations()
                .onSuccess { _affirmations.clear(); _affirmations.addAll(it) }
                .onFailure { /* Handle error */ }
        }
    }

    fun toggleLike(id: String) {
        if (likedSet.contains(id)) likedSet.remove(id)
        else likedSet.add(id)
    }

    fun isLiked(id: String) = likedSet.contains(id)

    fun addCustomAffirmation(text: String) {
        val new = Affirmation(id = "custom_${System.currentTimeMillis()}", text = text)
        _affirmations.add(0, new)
    }
}


