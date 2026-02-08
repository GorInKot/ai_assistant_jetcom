package com.example.ai_assistant.myui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ai_assistant.ChatViewModel

@Composable
fun ChatScreen(
    viewModel: ChatViewModel = viewModel()
) {
    var input by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().padding(16.dp)) {

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(viewModel.messages) { msg ->
                Text(msg)
                Spacer(Modifier.height(8.dp))
            }
        }

        Row {
            TextField(
                value = input,
                onValueChange = { input = it },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = {
                viewModel.sendMessage(input)
                input = ""
            }) {
                Text("Отправить")
            }
        }
    }
}

