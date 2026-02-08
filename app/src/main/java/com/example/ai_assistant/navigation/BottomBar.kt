package com.example.ai_assistant.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun BottomBar(navController: NavController) {

    NavigationBar {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(Screen.Chat.route) },
            label = { Text("Чат") },
            icon = {}
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(Screen.Documents.route) },
            label = { Text("Документы") },
            icon = {}
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(Screen.Actions.route) },
            label = { Text("Действия") },
            icon = {}
        )
    }
}