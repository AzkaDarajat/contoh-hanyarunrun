package com.example.hanyarunrun.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://data.jabarprov.go.id/api-backend/bigdata/disnakertrans/"

    val instance: DataJabarApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(DataJabarApiService::class.java)
    }
}