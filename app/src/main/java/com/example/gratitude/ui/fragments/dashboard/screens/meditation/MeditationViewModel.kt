package com.example.gratitude.ui.fragments.dashboard.screens.meditation

import android.util.Log
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.State
import com.example.gratitude.ui.fragments.dashboard.screens.meditation.model.YouTubeItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MeditationViewModel @Inject constructor(
    private val repository: MeditationRepository
) : ViewModel() {

    private val _videos = mutableStateListOf<YouTubeItem>()
    val videos: List<YouTubeItem> get() = _videos

    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> get() = _loading

    fun fetchVideos(query: String) {
        viewModelScope.launch {
            _loading.value = true
            repository.searchVideos(query)
                .onSuccess {
                    _videos.clear()
                    _videos.addAll(it)
                }
                .onFailure {
                    Log.e("MeditationViewModel", "Error: ${it.message}")
                }
            _loading.value = false
        }
    }
}
