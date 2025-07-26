package com.example.gratitude.ui.fragments.dashboard.screens.home.gratitudebuddy

import com.example.gratitude.network.APINetworkOperations
import com.example.gratitude.ui.fragments.dashboard.screens.home.gratitudebuddy.model.GratitudeBuddyRequest
import javax.inject.Inject


class GratitudeBuddyRepository @Inject constructor(
    private val apiService: APINetworkOperations
) {

    suspend fun getSelfCareReply(userMessage: String): Result<String> {
        return try {
            val request = GratitudeBuddyRequest(inputs = userMessage)

            val response = apiService.getSelfCareReply(
                url = "https://api-inference.huggingface.co/models/tiiuae/falcon-7b-instruct",
                request = request
            )

            if (response.isSuccessful && !response.body().isNullOrEmpty()) {
                Result.success(response.body()?.first()?.generated_text ?: "")
            } else {
                Result.failure(Exception("API failed with code ${response.code()}"))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
