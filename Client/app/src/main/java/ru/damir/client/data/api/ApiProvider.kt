package ru.damir.client.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiProvider {
    val api : Api
    init {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.43.75:8080")
            .build()
        api = retrofit.create(Api::class.java)
    }
}