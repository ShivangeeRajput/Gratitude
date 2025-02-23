package com.example.gratitude.models

data class User(
    val email: String,
    val mobile: String,
    val username: String
)

data class UserRequest(
    val message: String,
    val user: User
)