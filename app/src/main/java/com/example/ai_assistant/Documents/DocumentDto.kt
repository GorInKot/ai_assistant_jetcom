package com.example.ai_assistant.Documents

data class DocumentDto(
    val file_name: String?,
    val relative_path: String?,
    val process: String?,
    val extension: String?,
    val is_form: Boolean?,
    val searchable: Boolean?,
    val url: String?,
    val download_url: String?
)