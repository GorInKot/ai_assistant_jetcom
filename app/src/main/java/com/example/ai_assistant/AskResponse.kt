package com.example.ai_assistant

data class AskResponse(
    val answer: String,
    val sources: List<SourceDto>?,
    val no_exact_match: Boolean // отражает server-side поле
)