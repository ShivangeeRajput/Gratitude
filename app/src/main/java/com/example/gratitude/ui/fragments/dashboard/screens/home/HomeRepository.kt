package com.example.gratitude.ui.fragments.dashboard.screens.home


import com.example.gratitude.network.APINetworkOperations
import com.example.gratitude.ui.fragments.dashboard.screens.home.models.DailyQuote
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val apiService: APINetworkOperations
) {
    suspend fun getDailyQuote(): Result<DailyQuote> {
        return try {
            val response = apiService.getDailyQuote()
            if (response.isSuccessful && response.body() != null) {
                val quotes = response.body()!!.thoughts
                if (quotes.isNotEmpty()) {
                    val randomQuote = quotes.random()
                    Result.success(randomQuote)
                } else {
                    Result.failure(Exception("Empty quote list"))
                }
            } else {
                Result.failure(Exception("Failed with code ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getAllQuotes(): Result<List<DailyQuote>> {
        return try {
            val response = apiService.getDailyQuote()
            if (response.isSuccessful && response.body() != null) {
                val list = response.body()!!.thoughts
                Result.success(list)
            } else {
                Result.failure(Exception("Failed with code ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


}
