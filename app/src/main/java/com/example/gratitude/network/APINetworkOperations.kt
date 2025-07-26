package com.example.gratitude.network

import com.example.gratitude.ui.fragments.dashboard.screens.affirmations.model.AffirmationsResponse
import com.example.gratitude.ui.fragments.dashboard.screens.home.gratitudebuddy.model.GratitudeBuddyRequest
import com.example.gratitude.ui.fragments.dashboard.screens.home.gratitudebuddy.model.GratitudeBuddyResponse
import com.example.gratitude.ui.fragments.dashboard.screens.home.models.DailyQuotesResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface APINetworkOperations {
    @POST
    suspend fun getSelfCareReply(
        @Url url: String,
        @Body request: GratitudeBuddyRequest
    ): Response<List<GratitudeBuddyResponse>>

    @GET("/getDailyQuotes")
    suspend fun getDailyQuote(): Response<DailyQuotesResponse>

    @GET("affirmations")
    suspend fun getAffirmations(): Response<AffirmationsResponse>



}
