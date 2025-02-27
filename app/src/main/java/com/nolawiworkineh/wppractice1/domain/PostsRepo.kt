package com.nolawiworkineh.wppractice1.domain

import com.nolawiworkineh.wppractice1.data.PostsModel

interface PostsRepo {
    suspend fun getPosts(): List<PostsModel>
    suspend fun getPostById(id: String): PostsModel
}