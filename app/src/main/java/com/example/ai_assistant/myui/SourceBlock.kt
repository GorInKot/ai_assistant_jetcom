package com.example.ai_assistant.ui.chat

import com.example.ai_assistant.Source
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ai_assistant.ui.theme.AccentColor
import com.example.ai_assistant.ui.theme.AccentSoftColor
import androidx.compose.ui.platform.LocalUriHandler

@Composable
fun SourcesBlock(sources: List<String>) {
    Column(
        modifier = Modifier
            .padding(top = 8.dp)
            .background(AccentSoftColor, RoundedCornerShape(10.dp))
            .padding(8.dp)
    ) {
        Text(
            text = "Источники",
            color = AccentColor,
            style = androidx.compose.material3.MaterialTheme.typography.labelMedium
        )

        Spacer(Modifier.height(4.dp))

        sources.forEach { src ->
            Text(
                text = src,
                style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(vertical = 2.dp)
            )
        }
    }
}

