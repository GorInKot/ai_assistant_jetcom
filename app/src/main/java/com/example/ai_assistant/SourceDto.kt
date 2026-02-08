package com.example.ai_assistant

data class SourceDto(
    val file_name: String?,
    val relative_path: String?,
    val pages: List<Int>?,
    val sections: List<String>?,
    val source_type: String?,
    val url: String?,
    val download_url: String?
)
