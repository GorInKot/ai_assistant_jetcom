package com.example.ai_assistant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ai_assistant.Actions.ActionsScreen
import com.example.ai_assistant.Documents.DocumentsScreen
import com.example.ai_assistant.navigation.BottomBar
import com.example.ai_assistant.navigation.Screen
import com.example.ai_assistant.ui.chat.ChatScreen
import com.example.ai_assistant.ui.theme.AiassistantTheme
import com.example.ai_assistant.ui.theme.ColorSurf


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AiassistantTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()

            }

        }
    }
}

    @Composable
    fun MainScreen() {
        val navController = rememberNavController()

        Scaffold(
            topBar = {
                // Простая кастомная шапка
                Surface(
                    color = ColorSurf,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.CenterStart,
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        Text(
                            text = "Корпоративный ИИ-агент",
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        )
                    }
                }
            },
            bottomBar = { BottomBar(navController) }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Chat.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.Chat.route) { ChatScreen() }
                composable(Screen.Documents.route) { DocumentsScreen() }
                composable(Screen.Actions.route) { ActionsScreen() }
            }
        }
    }
    }
