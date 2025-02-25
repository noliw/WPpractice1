package com.nolawiworkineh.wppractice1.data

import android.util.Log
import com.nolawiworkineh.wppractice1.domain.PostsRepo
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class MukundRepositoryImpl @Inject constructor(
    private val apiService: MukundApiService
) : PostsRepo {
    override suspend fun getPosts(): List<MukundApiModel> {
       return try {
    apiService.getPosts()
        } catch (e: Exception){
            when (e){
                is CancellationException -> throw e
                else -> Log.e("MukundRepositoryImplError", "The following error occured ${e.message}")
            }
            emptyList()
        }
    }

}