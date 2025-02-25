package com.nolawiworkineh.wppractice1.domain

import com.nolawiworkineh.wppractice1.data.MukundApiModel

interface PostsRepo {
    suspend fun getPosts(): List<MukundApiModel>
}