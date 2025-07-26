package com.example.gratitude.ui.fragments.dashboard.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gratitude.ui.fragments.dashboard.screens.home.models.DailyQuote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val quoteRepository: HomeRepository
) : ViewModel() {

    private val _quote = MutableLiveData<DailyQuote>()
    val quote: LiveData<DailyQuote> get() = _quote

    private val _allQuotes = MutableLiveData<List<DailyQuote>>()
    val allQuotes: LiveData<List<DailyQuote>> = _allQuotes

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchDailyQuote() {
        viewModelScope.launch {
            _loading.value = true
            quoteRepository.getDailyQuote()
                .onSuccess { dailyQuote ->
                    _quote.value = dailyQuote
                }
                .onFailure { e ->
                    _error.value = e.message
                }
            _loading.value = false
        }
    }

    fun fetchAllQuotes() {
        viewModelScope.launch {
            quoteRepository.getAllQuotes()
                .onSuccess { quotes ->
                    _allQuotes.value = quotes
                }
                .onFailure { e ->
                    _error.value = e.message
                }
        }
    }

}