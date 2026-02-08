package com.example.ai_assistant

data class AskRequest(
    val question: String,
    val session_id: String
)
