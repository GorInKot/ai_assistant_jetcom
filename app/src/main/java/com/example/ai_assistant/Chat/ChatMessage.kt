package com.example.ai_assistant.Chat

enum class Role { USER, ASSISTANT }

data class ChatMessage(
    val text: String,
    val role: Role,
    val sources: List<String>? = null
)