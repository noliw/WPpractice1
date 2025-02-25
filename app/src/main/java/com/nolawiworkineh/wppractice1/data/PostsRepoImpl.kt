package com.nolawiworkineh.wppractice1.data

import android.util.Log
import com.nolawiworkineh.wppractice1.domain.PostsRepo
import kotlinx.coroutines.CancellationException
import javax.inject.Inject

class PostsRepoImpl @Inject constructor(
    private val apiService: ApiService
): PostsRepo {
    override suspend fun getPosts(): List<PostsModel> {
        return try {
            Log.e("PostsRepoImpl", "Successfully fetched data")
            apiService.getPosts()
        } catch (e: Exception){
            when (e){
                is CancellationException -> throw e
                else -> Log.e("PostsRepoImpl", "The following error occurred ${e.message}")
            }
            emptyList()
        }
    }
}