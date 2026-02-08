package com.example.ai_assistant.Actions

data class ActionDto(
    val action_id: String?,
    val title: String?,
    val details: String?,
    val process: String?,
    val action_type: String?,
    val status: String?,
    val requester: String?,
    val created_at: String?
)