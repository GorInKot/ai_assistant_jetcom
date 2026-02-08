package com.example.ai_assistant

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkModule {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    // Создаем OkHttpClient с увеличенными тайм-аутами
    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, java.util.concurrent.TimeUnit.SECONDS) // соединение
        .readTimeout(120, java.util.concurrent.TimeUnit.SECONDS)   // чтение ответа
        .writeTimeout(120, java.util.concurrent.TimeUnit.SECONDS)  // отправка тела запроса
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()

    val api: AssistantApi = retrofit.create(AssistantApi::class.java)
}


