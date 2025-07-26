package com.example.gratitude.ui.fragments.dashboard.screens.journal

import javax.inject.Inject

class JournalRepository @Inject constructor(
    private val dao: JournalDao
) {
    suspend fun getAllEntries(): List<JournalEntry> = dao.getAll()

    suspend fun insertEntry(entry: JournalEntry) = dao.insert(entry)

    suspend fun searchEntries(query: String): List<JournalEntry> = dao.searchByContent(query)

    suspend fun updateEntry(entry: JournalEntry) = dao.update(entry)

    suspend fun deleteEntry(entry: JournalEntry) = dao.delete(entry)
}
