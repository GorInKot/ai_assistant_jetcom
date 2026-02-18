package com.example.ai_assistant.Actions

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ai_assistant.myui.EmptyHint
import com.example.ai_assistant.myui.Actions.Action
import com.example.ai_assistant.ui.theme.AccentColor
import com.example.ai_assistant.ui.theme.Panel
import com.example.ai_assistant.ui.theme.SubtleColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionsScreen(viewModel: ActionsViewModel = viewModel()) {

    var expanded by remember { mutableStateOf(false) }
    val actionTypes = listOf(
        "Заявка на транспорт",
        "Заявка на обучение",
        "Заявка на медосмотр",
        "Регистрация обращения"
    )

    var selectedType by remember { mutableStateOf(actionTypes[0]) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text("Действия", style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(8.dp))

        // 🔽 Выпадающий список типа действия
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedType,
                onValueChange = {},
                readOnly = true,
                placeholder = { Text("Тип действия") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                actionTypes.forEach { type ->
                    DropdownMenuItem(
                        text = { Text(type) },
                        onClick = {
                            selectedType = type
                            expanded = false
                        }
                    )
                }
            }
        }

        // 🔹 Поле ввода процесса
        TextField(
            value = viewModel.actionDetails,
            onValueChange = { viewModel.updateDetails(it) },
            placeholder = { Text("ЕКТП_Транспорт") },
            modifier = Modifier.fillMaxWidth()
        )

        // 🔹 Поле для темы
        TextField(
            value = viewModel.actionTitle,
            onValueChange = { viewModel.updateTitle(it) },
            placeholder = { Text("Тема заявки/обращения") },
            modifier = Modifier.fillMaxWidth()
        )

        // 🔹 Поле для описания (растягивается по мере ввода)
        TextField(
            value = viewModel.actionDetails,
            onValueChange = { viewModel.updateDetails(it) },
            placeholder = { Text("Описание") },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp, max = 300.dp),
            maxLines = Int.MAX_VALUE
        )

        // 🔹 Кнопка регистрации
        Button(
            onClick = {
                viewModel.createAction()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Зарегистрировать действие")
        }

        // 🔹 Сообщение о статусе
        if (viewModel.statusMessage.isNotEmpty()) {
            Text(viewModel.statusMessage, color = AccentColor)
        }

        Spacer(Modifier.height(16.dp))

        Text("Последние действия", style = MaterialTheme.typography.titleMedium)

        // 🔹 Список существующих действий
        if (viewModel.actions.isEmpty()) {
            EmptyHint("Действия не найдены")
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(viewModel.actions) { action ->
                    ActionItem(action)
                }
            }
        }
    }
}

@Composable
fun ActionItem(action: Action) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Panel)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = action.title, style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(4.dp))
            Text(text = action.details, style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(4.dp))
            Text(text = "Процесс: ${action.process}", color = SubtleColor)
            Text(text = "Статус: ${action.status}", color = AccentColor)
            Text(text = "Создатель: ${action.requester}", color = SubtleColor)
            Text(text = "Дата: ${action.createdAt}", color = SubtleColor)
        }
    }
}
