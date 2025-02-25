package com.nolawiworkineh.wppractice1.data

import retrofit2.http.GET

interface MukundApiService {
    @GET("posts")
    suspend fun getPosts(): List<MukundApiModel>
}