package com.nolawiworkineh.wppractice1.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nolawiworkineh.wppractice1.data.PostsModel
import com.nolawiworkineh.wppractice1.domain.PostsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException


@HiltViewModel
class PostsViewModel @Inject constructor(
    private val postsRepo: PostsRepo
) : ViewModel() {

    private val _state = MutableStateFlow<List<PostsModel>>(emptyList())
    var state = _state


    init {
        getPosts()
    }

    private fun getPosts() {
        viewModelScope.launch {
            try {
                _state.value = postsRepo.getPosts()
            } catch (e: Exception) {
                when (e) {
                    is CancellationException -> throw e
                    else -> Log.e("PostsViewModel", "The following error occurred ${e.message}")
                }
            }
        }

    }
}