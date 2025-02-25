package com.nolawiworkineh.wppractice1.data

import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): List<PostsModel>
}