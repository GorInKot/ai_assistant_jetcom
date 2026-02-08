package com.example.ai_assistant

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.UUID

data class ChatMessage(val text: String, val isUser: Boolean)

class ChatViewModel : ViewModel() {

    var messages by mutableStateOf(listOf<String>())
        private set

    fun sendMessage(text: String) {
        messages = messages + "Ты: $text"

        viewModelScope.launch {
            try {
                val response = NetworkModule.api.ask(
                    AskRequest(
                        question = text,
                        session_id = "android"
                    )
                )
                messages = messages + "Бот: ${response.answer}"
            } catch (e: Exception) {
                messages = messages + "Ошибка: ${e.message}"
            }
        }
    }
}
