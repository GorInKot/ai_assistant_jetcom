package com.example.ai_assistant

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Расширение для Context: создаем preferences DataStore
val Context.dataStore by preferencesDataStore(name = "token_prefs")

class TokenDataStore(private val context: Context) {

    private val TOKEN_KEY = stringPreferencesKey("auth_token")

    // поток токена для подписки
    fun getToken(): Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[TOKEN_KEY]
        }

    // сохранить токен
    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    // удалить токен
    suspend fun clearToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }
}
