package com.example.ai_assistant.myui.Actions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ai_assistant.ui.theme.Panel
import com.example.ai_assistant.ui.theme.SubtleColor

data class Action(
    val id: String,
    val title: String,
    val details: String,
    val process: String,
    val actionType: String,
    val status: String,
    val requester: String,
    val createdAt: String
)

@Composable
fun ActionItem(action: Action) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Panel)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text("${action.title} [${action.status}]")
            Text("ID: ${action.id} | ${action.process} | ${action.actionType}", color = SubtleColor)
            Text(action.details, color = SubtleColor)
            Text("Инициатор: ${action.requester} | ${action.createdAt}", color = SubtleColor)
        }
    }
}
