package com.example.ai_assistant.myui.Documents

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ai_assistant.Documents.DocumentsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentsView(
    viewModel: DocumentsViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var showFormsOnly by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        // 🔎 Поиск
        TextField(
            value = viewModel.searchQuery,
            onValueChange = { viewModel.updateSearchQuery(it) },
            placeholder = { Text("Поиск документа") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = androidx.compose.foundation.text.KeyboardOptions.Default.copy(
                imeAction = androidx.compose.ui.text.input.ImeAction.Search
            ),
            keyboardActions = androidx.compose.foundation.text.KeyboardActions(
                onSearch = { viewModel.loadDocuments(formsOnly = showFormsOnly) }
            )
        )

        Spacer(Modifier.height(8.dp))

        // 🔽 Процесс + чекбокс
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.weight(1f)
            ) {
                TextField(
                    value = viewModel.selectedProcess.label,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) }
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DocumentsViewModel.ProcessFilter.values().forEach { filter ->
                        DropdownMenuItem(
                            text = { Text(filter.label) },
                            onClick = {
                                viewModel.updateProcess(filter)
                                expanded = false
                                viewModel.loadDocuments(formsOnly = showFormsOnly)
                            }
                        )
                    }
                }
            }

            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Checkbox(
                    checked = showFormsOnly,
                    onCheckedChange = {
                        showFormsOnly = it
                        viewModel.loadDocuments(formsOnly = showFormsOnly)
                    }
                )
                Text("Только формы")
            }
        }

        Spacer(Modifier.height(12.dp))

        // Список документов
        val filteredDocuments = remember(viewModel.documents, showFormsOnly) {
            if (showFormsOnly) viewModel.documents.filter { it.isForm } else viewModel.documents
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            if (filteredDocuments.isEmpty()) {
                item { Text("Документы не найдены") }
            } else {
                items(filteredDocuments) { doc ->
                    DocumentItem(doc = doc)
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
    }
}
