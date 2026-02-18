package com.example.ai_assistant.myui.Documents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalUriHandler
import com.example.ai_assistant.ui.theme.AccentColor
import com.example.ai_assistant.ui.theme.AccentSoftColor
import com.example.ai_assistant.ui.theme.Panel
import com.example.ai_assistant.ui.theme.SubtleColor

data class Document(
    val fileName: String,
    val process: String,
    val path: String,
    val isForm: Boolean,
    val searchable: Boolean,
    val url: String,
    val downloadUrl: String
)

@Composable
fun DocumentItem(doc: Document) {
    val uriHandler = LocalUriHandler.current

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Panel)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            // Название документа
            Text(
                text = doc.fileName
            )

            // Форма (если есть)
            if (doc.isForm) {
                Text(
                    text = "Форма",
                    color = AccentColor,
                    modifier = Modifier
                        .background(
                            AccentSoftColor,
                            RoundedCornerShape(50)
                        )
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                )
            }

            // Статус текста
            Text(
                text = if (doc.searchable)
                    "Текст доступен"
                else
                    "Без извлечения текста",
                color = SubtleColor
            )

            // Путь + процесс
            Text(
                text = "${doc.path} | ${doc.process}",
                color = SubtleColor,
                modifier = Modifier.padding(top = 4.dp)
            )

            // Кнопки
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(top = 6.dp)
            ) {
                Button(
                    onClick = { uriHandler.openUri(doc.url) }
                ) {
                    Text("Открыть")
                }

                Button(
                    onClick = { uriHandler.openUri(doc.downloadUrl) }
                ) {
                    Text("Скачать")
                }
            }
        }
    }
}


