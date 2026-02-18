package com.example.ai_assistant.Registration

data class TokenResponse(
    val access_token: String,
    val token_type: String = "bearer"
)
