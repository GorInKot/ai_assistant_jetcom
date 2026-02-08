package com.example.ai_assistant.myui.Documents

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ai_assistant.Documents.DocumentsViewModel
import com.example.ai_assistant.myui.EmptyHint
import com.example.ai_assistant.ui.theme.Panel

@Composable
fun DocumentsView(viewModel: DocumentsViewModel = viewModel()) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {

        // Filters
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TextField(
                value = viewModel.searchQuery,
                onValueChange = { viewModel.updateSearchQuery(it) },
                placeholder = { Text("Поиск по названию/пути документа") },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { viewModel.loadDocuments() })
            )

            TextField(
                value = viewModel.selectedProcess,
                onValueChange = { viewModel.updateProcess(it) },
                placeholder = { Text("Процесс") },
                modifier = Modifier.width(160.dp)
            )

            Button(onClick = { viewModel.loadDocuments() }) { Text("Обновить") }
        }

        Spacer(Modifier.height(12.dp))

        // Documents list
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            if (viewModel.documents.isEmpty()) {
                item {
                    EmptyHint("Документы не найдены.")
                }
            } else {
                items(viewModel.documents) { doc ->
                    DocumentItem(doc)
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
    }
}