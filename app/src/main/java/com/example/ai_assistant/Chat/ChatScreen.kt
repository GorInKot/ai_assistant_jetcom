package com.example.ai_assistant.ui.chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ai_assistant.Chat.ChatViewModel
import com.example.ai_assistant.myui.EmptyHint

@Composable
fun ChatScreen(viewModel: ChatViewModel = viewModel()) {
    val scrollState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    Column(Modifier.fillMaxSize()) {

        // Messages list
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            state = scrollState
        ) {
            if (viewModel.messages.isEmpty()) {
                item {
                    EmptyHint("Задайте вопрос, например: \"Что такое ЕКТП?\"")
                }
            } else {
                items(viewModel.messages) { msg ->
                    MessageCard(msg)
                    Spacer(Modifier.height(8.dp))
                }
            }
        }

        // Auto-scroll to bottom
        LaunchedEffect(viewModel.messages.size) {
            if (viewModel.messages.isNotEmpty()) {
                scrollState.animateScrollToItem(viewModel.messages.size - 1)
            }
        }

        // Input field
        ChatInput(
            text = viewModel.inputText,
            onTextChange = { viewModel.updateInput(it) },
            onSend = { viewModel.sendMessage() }
        )
    }
}
