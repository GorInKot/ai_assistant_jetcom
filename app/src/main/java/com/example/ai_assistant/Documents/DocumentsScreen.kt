package com.example.ai_assistant.Documents

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ai_assistant.myui.Documents.DocumentsView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentsScreen(viewModel: DocumentsViewModel = viewModel()) {
    DocumentsView(viewModel)
}
