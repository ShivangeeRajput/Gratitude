package com.example.gratitude.ui.fragments.dashboard.screens.journal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class JournalViewModel @Inject constructor(
    private val repository: JournalRepository
) : ViewModel() {


    private val _filteredJournalList = MutableStateFlow<List<JournalEntry>>(emptyList())
    val filteredJournalList: StateFlow<List<JournalEntry>> = _filteredJournalList.asStateFlow()

    init {
        loadAllEntries()
    }

    fun loadAllEntries() {
        viewModelScope.launch {
            _filteredJournalList.value = repository.getAllEntries()
        }
    }

    fun insertJournalEntry(content: String) {
        viewModelScope.launch {
            repository.insertEntry(
                JournalEntry(content = content)
            )
            loadAllEntries()
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            if (query.isBlank()) {
                loadAllEntries()
            } else {
                _filteredJournalList.value = repository.searchEntries(query)
            }
        }
    }

    fun updateJournalEntry(entry: JournalEntry) {
        viewModelScope.launch {
            repository.updateEntry(entry)
            loadAllEntries()
        }
    }

    fun deleteJournalEntry(entry: JournalEntry) {
        viewModelScope.launch {
            repository.deleteEntry(entry)
            loadAllEntries()
        }
    }

}
