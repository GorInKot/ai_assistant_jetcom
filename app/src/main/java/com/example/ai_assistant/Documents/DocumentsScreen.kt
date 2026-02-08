package com.example.ai_assistant.Documents

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DocumentsScreen(viewModel: DocumentsViewModel = viewModel()) {
    val documents = viewModel.documents
    val isLoading by remember { mutableStateOf(viewModel.isLoading) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TextField(
                value = viewModel.searchQuery,
                onValueChange = { viewModel.updateSearchQuery(it) },
                label = { Text("Поиск") },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = { viewModel.loadDocuments() }) {
                Text("Поиск")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            Text("Загрузка...", style = MaterialTheme.typography.bodyMedium)
        } else if (documents.isEmpty()) {
            Text("Документы не найдены", style = MaterialTheme.typography.bodyMedium)
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(documents) { doc ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(doc.fileName, style = MaterialTheme.typography.titleMedium)
                            Text("Процесс: ${doc.process}", style = MaterialTheme.typography.bodySmall)
                            Text("Путь: ${doc.path}", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}

