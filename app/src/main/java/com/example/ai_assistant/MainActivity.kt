package com.example.ai_assistant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ai_assistant.Actions.ActionsScreen
import com.example.ai_assistant.Chat.ChatViewModel
import com.example.ai_assistant.Documents.DocumentsScreen
import com.example.ai_assistant.navigation.AppNavHost
import com.example.ai_assistant.navigation.BottomBar
import com.example.ai_assistant.navigation.Screen
import com.example.ai_assistant.ui.chat.ChatScreen
import com.example.ai_assistant.ui.theme.AiassistantTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AiassistantTheme {
                MainScreen()
            }

        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navController) } // передаем navController в BottomBar
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Chat.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Screen.Chat.route) { ChatScreen() }
            composable(Screen.Documents.route) { DocumentsScreen() }
            composable(Screen.Actions.route) { ActionsScreen() }
        }
    }
}
