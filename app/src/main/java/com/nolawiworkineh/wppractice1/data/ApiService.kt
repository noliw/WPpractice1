package com.nolawiworkineh.wppractice1.data

import retrofit2.http.GET

interface ApiService {
// using an interface lets retrofit generate the the implementation if we used a class
    // we would have to do it ourselves
    @GET("posts")
    suspend fun getPosts(): List<PostsModel>
    // if data is not static use flow Flow<List<PostsModel>>
}