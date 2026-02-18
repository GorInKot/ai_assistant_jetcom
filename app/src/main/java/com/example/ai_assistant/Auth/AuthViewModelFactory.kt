package com.example.ai_assistant.Auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ai_assistant.AssistantApi
import com.example.ai_assistant.TokenDataStore

class AuthViewModelFactory(
    private val tokenStore: TokenDataStore
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(tokenStore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

