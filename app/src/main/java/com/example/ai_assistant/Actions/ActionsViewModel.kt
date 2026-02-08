package com.example.ai_assistant.Actions

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ai_assistant.NetworkModule
import com.example.ai_assistant.Actions.CreateActionRequest
import com.example.ai_assistant.myui.Actions.Action
import kotlinx.coroutines.launch
import java.util.UUID

class ActionsViewModel : ViewModel() {

    val actions = mutableStateListOf<Action>()

    var actionTitle by mutableStateOf("")
        private set

    var actionDetails by mutableStateOf("")
        private set

    var statusMessage by mutableStateOf("")
        private set

    fun updateTitle(value: String) {
        actionTitle = value
    }

    fun updateDetails(value: String) {
        actionDetails = value
    }

    fun createAction() {
        val title = actionTitle.trim()
        val details = actionDetails.trim()

        if (title.isEmpty() || details.isEmpty()) {
            statusMessage = "Введите тему и описание"
            return
        }

        viewModelScope.launch {
            try {
                NetworkModule.api.createAction(
                    CreateActionRequest(
                        action_type = "manual",
                        process = "ЕКТП_Транспорт",
                        title = title,
                        details = details,
                        requester = "Android"
                    )
                )

                actions.add(
                    Action(
                        id = UUID.randomUUID().toString(),
                        title = title,
                        details = details,
                        process = "ЕКТП_Транспорт",
                        actionType = "manual",
                        status = "Создано",
                        requester = "Android",
                        createdAt = System.currentTimeMillis().toString()
                    )
                )

                actionTitle = ""
                actionDetails = ""
                statusMessage = "Действие зарегистрировано"

            } catch (e: Exception) {
                statusMessage = "Ошибка: ${e.message}"
            }
        }
    }

    fun loadActions() {
        viewModelScope.launch {
            actions.clear()
            try {
                val response = NetworkModule.api.getActions()
                response.actions.forEach { raw ->
                    actions.add(
                        Action(
                            id = raw.action_id ?: "",
                            title = raw.title ?: "",
                            details = raw.details ?: "",
                            process = raw.process ?: "",
                            actionType = raw.action_type ?: "",
                            status = raw.status ?: "",
                            requester = raw.requester ?: "",
                            createdAt = raw.created_at ?: ""
                        )
                    )
                }
            } catch (_: Exception) {
                // можно добавить лог
            }
        }
    }

    init {
        loadActions()
    }
}