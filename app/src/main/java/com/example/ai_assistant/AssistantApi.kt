package com.example.ai_assistant

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

//interface AssistantApi {
//    @POST("/ask")  // метод POST, путь /ask как в твоем Python FastAPI
//    suspend fun ask(@Body request: AskRequest): AskResponse
//}

data class AskRequest(
    val question: String,
    val session_id: String
)
data class AskResponse(
    val answer: String,
    val sources: List<Any>,
    val no_exact_match: Boolean
)

interface AssistantApi {
    @POST("api/ask")
    suspend fun ask(
        @Body request: AskRequest
    ): AskResponse
}



