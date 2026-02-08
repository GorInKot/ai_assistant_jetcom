package com.example.ai_assistant

import com.example.ai_assistant.myui.ChatMessage
import com.example.ai_assistant.myui.Role
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ai_assistant.NetworkModule
import com.example.ai_assistant.AskRequest
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {

    val messages = mutableStateListOf<ChatMessage>()
    var inputText by mutableStateOf("")
        private set

    fun updateInput(text: String) {
        inputText = text
    }

    fun sendMessage() {
        val text = inputText.trim()
        if (text.isEmpty()) return

        // Сообщение пользователя
        messages.add(ChatMessage(text, Role.USER))
        inputText = ""

        viewModelScope.launch {
            try {
                val response = NetworkModule.api.ask(
                    AskRequest(
                        question = text,
                        session_id = "android"
                    )
                )

                // Если response.sources — это List<String>, просто используем как есть
                val sourcesList: List<String> = (response.sources ?: emptyList()).map { it.toString() }


                // Ответ ассистента
                messages.add(ChatMessage(
                    text = response.answer,
                    role = Role.ASSISTANT,
                    sources = sourcesList
                ))

            } catch (e: Exception) {
                messages.add(ChatMessage("Ошибка: ${e.message}", Role.ASSISTANT))
            }
        }
    }
}
