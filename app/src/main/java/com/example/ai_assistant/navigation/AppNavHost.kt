package com.example.ai_assistant.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ai_assistant.Documents.DocumentsScreen
import com.example.ai_assistant.Actions.ActionsScreen
import com.example.ai_assistant.ui.chat.ChatScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Chat.route,
        modifier = modifier
    ) {
        composable(Screen.Chat.route) {
            ChatScreen()
        }
        composable(Screen.Documents.route) {
            DocumentsScreen()
        }
        composable(Screen.Actions.route) {
            ActionsScreen()
        }
    }
}

