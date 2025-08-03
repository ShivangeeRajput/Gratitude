package com.example.gratitude.ui.fragments.dashboard.screens.journal

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class JournalEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val content: String,
    val timestamp: Long = System.currentTimeMillis()
)
