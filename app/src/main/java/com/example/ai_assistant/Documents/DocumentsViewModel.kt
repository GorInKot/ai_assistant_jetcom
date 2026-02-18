package com.example.ai_assistant.Documents

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ai_assistant.NetworkModule
import com.example.ai_assistant.myui.Documents.Document
import kotlinx.coroutines.launch

class DocumentsViewModel : ViewModel() {

    val documents = mutableStateListOf<Document>()

    var searchQuery by mutableStateOf("")
        private set

    var selectedProcess by mutableStateOf(ProcessFilter.ALL)
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun updateSearchQuery(value: String) { searchQuery = value }

    fun updateProcess(filter: ProcessFilter) { selectedProcess = filter }

    fun loadDocuments(formsOnly: Boolean = false) {
        viewModelScope.launch {
            isLoading = true
            documents.clear()

            try {
                val response = NetworkModule.api.getDocuments(
                    query = searchQuery.ifBlank { null },
                    process = if (selectedProcess == ProcessFilter.ALL) null else selectedProcess.label
                )

                response.documents.forEach { raw ->
                    val doc = Document(
                        fileName = raw.file_name ?: "Документ",
                        process = raw.process ?: "",
                        path = raw.relative_path ?: "",
                        isForm = raw.is_form == true,
                        searchable = raw.searchable == true,
                        url = raw.url ?: "",
                        downloadUrl = raw.download_url ?: ""
                    )

                    if (!formsOnly || doc.isForm) documents.add(doc)
                }
            } catch (_: Exception) { }
            finally { isLoading = false }
        }
    }

    enum class ProcessFilter(val label: String) {
        ALL("Все процессы"),
        ZUS("ЦУС"),
        EKT("ЕКТП"),
        MED("Медосмотр")
    }

    init { loadDocuments() }
}
