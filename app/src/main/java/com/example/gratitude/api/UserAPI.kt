package com.example.gratitude.api

import com.example.gratitude.models.UserRequest
import com.example.gratitude.models.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPI {

    @POST
    suspend fun signUp(@Body userRequest: UserRequest): Response<UserResponse>

    @POST
    suspend fun signIn(@Body userRequest: UserRequest): Response<UserResponse>


}