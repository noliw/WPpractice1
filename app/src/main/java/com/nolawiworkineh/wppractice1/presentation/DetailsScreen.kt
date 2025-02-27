package com.nolawiworkineh.wppractice1.presentation


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel



@Composable
fun PostDetailScreen(
    postId: Int,
    viewModel: PostsViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val post = viewModel.idState.collectAsState().value

    LaunchedEffect(postId) {
        if (post == null) {  // ✅ Prevents unnecessary duplicate API calls
            viewModel.getPostById(postId) // ✅ Fetch post when screen opens
        }
    }

    when {
        post == null -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
            ) {
                CircularProgressIndicator() // ✅ Show loading state
            }
        }

        else -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = post?.title ?: "No Title Available", // ✅ Null-safe access
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = post?.body ?: "No Content Available", // ✅ Null-safe access
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
