package com.nolawiworkineh.wppractice1.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MukundsRetrofitClient {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
