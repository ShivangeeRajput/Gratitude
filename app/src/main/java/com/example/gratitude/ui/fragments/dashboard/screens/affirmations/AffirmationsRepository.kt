package com.example.gratitude.ui.fragments.dashboard.screens.affirmations

import com.example.gratitude.network.APINetworkOperations
import com.example.gratitude.ui.fragments.dashboard.screens.affirmations.model.Affirmation
import javax.inject.Inject

class AffirmationsRepository @Inject constructor(
    private val apiService: APINetworkOperations
) {
    suspend fun getAllAffirmations(): Result<List<Affirmation>> {
        return try {
            val response = apiService.getAffirmations() // This should return AffirmationsResponse
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!.affirmations)
            } else {
                Result.failure(Exception("Failed with code ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
