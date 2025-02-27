package com.nolawiworkineh.wppractice1.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nolawiworkineh.wppractice1.data.PostsModel
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
) : ViewModel() {

    private val _postsState = MutableStateFlow<List<PostsModel>>(emptyList())
//    var state = _state

    var postsState : StateFlow<List<PostsModel>> = _postsState.onStart {
        getPosts()
    }
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _idState = MutableStateFlow<PostsModel?>(null)
//    var state = _state

    var idState : StateFlow<PostsModel?> = _idState
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    private fun getPosts() {
        viewModelScope.launch {
            try {
                _postsState.value = postsRepo.getPosts()
            } catch (e: Exception) {
                when (e) {
                    is CancellationException -> throw e
                    else -> Log.e("PostsViewModel", "The following error occurred ${e.message}")
                }
            }
        }

    }

    fun getPostById(id: String) {
        viewModelScope.launch {
            try {
                _idState.value = postsRepo.getPostById(
                    id
                )
            } catch (e: Exception) {
                when (e) {
                    is CancellationException -> throw e
                    else -> Log.e("PostsViewModel", "The following error occurred ${e.message}")
                }
            }
        }

    }
}