package com.example.ai_assistant.Actions

data class CreateActionRequest(
    val action_type: String,
    val process: String,
    val title: String,
    val details: String,
    val requester: String
)
