package com.nolawiworkineh.wppractice1.data

import retrofit2.http.Body

data class PostsModel(
    val id: String,
    val title: String,
    val body: String
)
