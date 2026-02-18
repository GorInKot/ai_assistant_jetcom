package com.example.ai_assistant.Registration

import com.squareup.moshi.Json

data class RegisterRequest(
    val email: String,
    val password: String,
    @Json(name = "full_name") val fullName: String?
)



