package com.example.ai_assistant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ai_assistant.ui.chat.ChatScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val chatViewModel: ChatViewModel = viewModel() // <-- получаем ViewModel

            MaterialTheme {
                Surface {
                    ChatScreen(viewModel = chatViewModel) // <-- передаём в ChatScreen
                }
            }
        }
    }
}