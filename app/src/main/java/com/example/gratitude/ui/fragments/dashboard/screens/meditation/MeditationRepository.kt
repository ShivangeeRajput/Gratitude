package com.example.gratitude.ui.fragments.dashboard.screens.meditation

import com.example.gratitude.AppConstants
import com.example.gratitude.network.YouTubeApiService
import com.example.gratitude.ui.fragments.dashboard.screens.meditation.model.YouTubeItem


import javax.inject.Inject

class MeditationRepository @Inject constructor(
    private val apiService: YouTubeApiService
) {
    suspend fun searchVideos(query: String): Result<List<YouTubeItem>> {
        return try {
            val response = apiService.searchVideos(
                query = query,
                apiKey = AppConstants.YT_API_KEY
            )

            Result.success(response.items)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
