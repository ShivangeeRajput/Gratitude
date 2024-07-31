package com.example.notify.api

import com.example.notify.models.UserRequest
import com.example.notify.models.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPI {

    @POST
    suspend fun signUp(@Body userRequest: UserRequest): Response<UserResponse>

    @POST
    suspend fun signIn(@Body userRequest: UserRequest): Response<UserResponse>


}