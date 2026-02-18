package com.example.ai_assistant.Auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ai_assistant.NetworkModule
import com.example.ai_assistant.TokenDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.ai_assistant.Registration.RegisterRequest
import com.example.ai_assistant.Registration.LoginRequest


sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val token: String) : AuthState()
    data class Error(val message: String) : AuthState()
}

class AuthViewModel(private val tokenStore: TokenDataStore) : ViewModel() {

    private val _state = MutableStateFlow<AuthState>(AuthState.Idle)
    val state: StateFlow<AuthState> = _state

    fun login(email: String, password: String) {
        _state.value = AuthState.Loading
        viewModelScope.launch {
            try {
                val response = NetworkModule.api.login(
                    LoginRequest(email, password)
                )
                tokenStore.saveToken(response.access_token)
                _state.value = AuthState.Success(response.access_token)
            } catch (e: Exception) {
                _state.value = AuthState.Error(e.message ?: "Ошибка входа")
            }
        }
    }

    fun register(email: String, password: String, fullName: String? = null) {
        _state.value = AuthState.Loading
        viewModelScope.launch {
            try {
                val response = NetworkModule.api.register(
                    RegisterRequest(email, password, fullName)
                )
                tokenStore.saveToken(response.access_token)
                _state.value = AuthState.Success(response.access_token)
            } catch (e: Exception) {
                _state.value = AuthState.Error(e.message ?: "Ошибка регистрации")
            }
        }
    }
}
