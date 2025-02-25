package com.nolawiworkineh.wppractice1.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nolawiworkineh.wppractice1.data.MukundApiModel
import com.nolawiworkineh.wppractice1.domain.PostsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException


@HiltViewModel
class PostsViewModel @Inject constructor(
    private val postsRepo: PostsRepo
): ViewModel() {
    private val _state = MutableStateFlow<List<MukundApiModel>>(emptyList())
    val state: StateFlow<List<MukundApiModel>> = _state.onStart {
        getPosts()
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


    private fun getPosts(){
        viewModelScope.launch {
            try {
                val posts = postsRepo.getPosts()
                _state.value = posts
                Log.e("PostsViewModel", "Fetched posts: $posts")
            } catch (e: Exception) {
                when (e) {
                    is CancellationException -> throw e
                    else -> Log.e("PostsViewModel", "The following error occured ${e.message}")
                }
            }
        }
    }
}