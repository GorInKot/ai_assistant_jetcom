package com.example.ai_assistant.ui.chat

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
import com.example.ai_assistant.myui.ChatMessage
import com.example.ai_assistant.myui.Role
import com.example.ai_assistant.ui.theme.AssistantBg
import com.example.ai_assistant.ui.theme.SubtleColor
import com.example.ai_assistant.ui.theme.UserBg

@Composable
fun MessageCard(msg: ChatMessage) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = if (msg.role == Role.USER) "Пользователь" else "Ассистент",
            color = SubtleColor,
            style = androidx.compose.material3.MaterialTheme.typography.labelSmall
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (msg.role == Role.USER) UserBg else AssistantBg
            )
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(msg.text)
                msg.sources?.let { sources ->
                    if (sources.isNotEmpty()) {
                        SourcesBlock(sources)
                    }
                }
            }
        }
    }
}
