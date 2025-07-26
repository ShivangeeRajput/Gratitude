package com.example.gratitude.ui.fragments.dashboard.screens.home.gratitudebuddy

import android.util.Log
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
                url = "https://api-inference.huggingface.co/models/google/flan-t5-small", // updated
                request = request
            )

            if (response.isSuccessful) {
                val raw = response.body()
                Log.d("BuddyResponse", "Response: $raw") // 🪵 DEBUG LOG

                if (!raw.isNullOrEmpty()) {
                    Result.success(raw.first().generated_text)
                } else {
                    Result.failure(Exception("Empty response"))
                }
            } else {
                Result.failure(Exception("Failed with ${response.code()}"))
            }

        } catch (e: Exception) {
            Log.e("BuddyError", "Exception: ${e.localizedMessage}")
            Result.failure(e)
        }
    }

}
