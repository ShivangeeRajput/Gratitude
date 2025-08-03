package com.example.gratitude.ui.fragments.dashboard.screens.journal

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [JournalEntry::class], version = 1, exportSchema = false)
abstract class JournalDatabase : RoomDatabase() {
    abstract fun journalDao(): JournalDao
}
