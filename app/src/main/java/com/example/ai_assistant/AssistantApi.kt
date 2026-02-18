package com.example.ai_assistant

import com.example.ai_assistant.Actions.ActionsResponse
import com.example.ai_assistant.Actions.CreateActionRequest
import com.example.ai_assistant.Actions.CreateActionResponse
import com.example.ai_assistant.Auth.Profile.DialogClearRequest
import com.example.ai_assistant.Auth.Profile.ProfileResponse
import com.example.ai_assistant.Auth.Profile.StatusResponse
import com.example.ai_assistant.Documents.DocumentsResponse
import com.example.ai_assistant.Registration.LoginRequest
import com.example.ai_assistant.Registration.RegisterRequest
import com.example.ai_assistant.Registration.TokenResponse
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
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

    // регистрация и логин пользователя
    @POST("api/auth/register")
    suspend fun register(@Body request: RegisterRequest): TokenResponse

    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): TokenResponse

    // Профиль
    @GET("api/user/profile")
    suspend fun profile(@Header("Authorization") token: String): ProfileResponse

    // очистка диалога
    @POST("api/dialog/clear")
    suspend fun clearDialog(@Body request: DialogClearRequest): StatusResponse

    // загрузка файла
    @GET("api/files/{file_path}")
    suspend fun getFile(
        @Path("file_path") path: String,
        @Query("download") download: Int = 0
    ): ResponseBody



}



