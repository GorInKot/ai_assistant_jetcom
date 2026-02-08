package com.example.ai_assistant.myui

import com.example.ai_assistant.Source

enum class Role { USER, ASSISTANT }

data class ChatMessage(
    val text: String,
    val role: Role,
    val sources: List<String>? = null
)