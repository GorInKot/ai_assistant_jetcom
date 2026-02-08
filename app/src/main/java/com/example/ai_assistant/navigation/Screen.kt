package com.example.ai_assistant.navigation

sealed class Screen(val route: String) {
    object Chat : Screen("chat")
    object Documents : Screen("documents")
    object Actions : Screen("actions")
}