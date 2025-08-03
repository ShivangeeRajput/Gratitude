package com.example.gratitude.ui.fragments.dashboard.screens.journal

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface JournalDao {
    @Query("SELECT * FROM JournalEntry ORDER BY timestamp DESC")
    suspend fun getAll(): List<JournalEntry>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: JournalEntry)

    @Query("SELECT * FROM JournalEntry WHERE content LIKE '%' || :query || '%' ORDER BY timestamp DESC")
    suspend fun searchByContent(query: String): List<JournalEntry>

    @Update
    suspend fun update(entry: JournalEntry)

    @Delete
    suspend fun delete(entry: JournalEntry)

}
