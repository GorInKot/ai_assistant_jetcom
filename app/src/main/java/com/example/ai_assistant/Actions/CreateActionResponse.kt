package com.example.ai_assistant.Actions

data class CreateActionResponse(
    val status: String,
    val message: String,
    val action: ActionDto
)