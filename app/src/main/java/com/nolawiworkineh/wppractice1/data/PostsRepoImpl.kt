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

    override suspend fun getPostById(id: Int): PostsModel {
        return try {
            apiService.getPostById(id)
        } catch (e: Exception) {
            when (e) {
                is CancellationException -> throw e // ✅ Preserve coroutine cancellation
                else -> Log.e("PostsRepoImpl", "Error fetching post $id: ${e.message}")
            }
            PostsModel(id, "Error", "Failed to load post") // ✅ Return fallback data
        }
    }
}

/*
override fun getPosts(): Flow<List<PostsModel>> = flow {
    try {
        val posts = apiService.getPosts()
        emit(posts) // ✅ Emit API response
    } catch (e: Exception) {
        when (e) {
            is CancellationException -> throw e
            else -> Log.e("PostsRepoImpl", "Error: ${e.message}")
        }
        emit(emptyList()) // ✅ Emit fallback value
    }
}.flowOn(Dispatchers.IO) // ✅ Run on IO thread
*/