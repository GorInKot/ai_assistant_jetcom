package com.example.ai_assistant

import com.example.ai_assistant.Actions.ActionsResponse
import com.example.ai_assistant.Actions.CreateActionRequest
import com.example.ai_assistant.Actions.CreateActionResponse
import com.example.ai_assistant.Documents.DocumentsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AssistantApi {

    @POST("api/ask")
    suspend fun ask(
        @Body request: AskRequest
    ): AskResponse

    @GET("api/documents")
    suspend fun getDocuments(
        @Query("q") query: String?,
        @Query("process") process: String?,
        @Query("forms_only") formsOnly: Int = 0
    ): DocumentsResponse

    @POST("api/actions")
    suspend fun createAction(
        @Body request: CreateActionRequest
    ): CreateActionResponse

    @GET("api/actions")
    suspend fun getActions(
        @Query("limit") limit: Int = 20
    ): ActionsResponse
}



