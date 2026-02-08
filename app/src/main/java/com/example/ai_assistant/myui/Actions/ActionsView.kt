package com.example.ai_assistant.myui.Actions

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ai_assistant.Actions.ActionsViewModel
import com.example.ai_assistant.myui.EmptyHint
import com.example.ai_assistant.ui.theme.Panel

@Composable
fun ActionsView(viewModel: ActionsViewModel = viewModel()) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {

        // Form to create action
        Column(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
            TextField(
                value = viewModel.actionTitle,
                onValueChange = { viewModel.updateTitle(it) },
                placeholder = { Text("Тема заявки/обращения") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            TextField(
                value = viewModel.actionDetails,
                onValueChange = { viewModel.updateDetails(it) },
                placeholder = { Text("Описание") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            Button(onClick = { viewModel.createAction() }) { Text("Зарегистрировать действие") }
            Text(viewModel.statusMessage, modifier = Modifier.padding(top = 4.dp))
        }

        // Last actions list
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            if (viewModel.actions.isEmpty()) {
                item { EmptyHint("Пока нет зарегистрированных действий.") }
            } else {
                items(viewModel.actions) { action ->
                    ActionItem(action)
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
    }
}
