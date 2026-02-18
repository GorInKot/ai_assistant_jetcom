package com.example.ai_assistant.Auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ai_assistant.NetworkModule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.ai_assistant.Registration.RegisterRequest

sealed class RegistrationState {
    object Idle : RegistrationState()
    object Loading : RegistrationState()
    data class Success(val token: String) : RegistrationState()
    data class Error(val message: String) : RegistrationState()
}

class RegistrationViewModel : ViewModel() {

    private val _regState = MutableStateFlow<RegistrationState>(RegistrationState.Idle)
    val regState: StateFlow<RegistrationState> = _regState

    fun register(email: String, password: String, fullName: String?) {
        if (email.isBlank() || password.isBlank()) {
            _regState.value = RegistrationState.Error("Email и пароль обязательны")
            return
        }

        viewModelScope.launch {
            _regState.value = RegistrationState.Loading
            try {
                val response = NetworkModule.api.register(
                    RegisterRequest(email, password, fullName)
                )
                _regState.value = RegistrationState.Success(response.access_token)
            } catch (e: Exception) {
                _regState.value = RegistrationState.Error(e.localizedMessage ?: "Ошибка регистрации")
            }
        }
    }

    fun resetState() {
        _regState.value = RegistrationState.Idle
    }
}

// DTO для API
data class RegisterRequest(
    val email: String,
    val password: String,
    val full_name: String? = null
)
