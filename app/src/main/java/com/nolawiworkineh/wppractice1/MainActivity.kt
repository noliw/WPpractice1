package com.nolawiworkineh.wppractice1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nolawiworkineh.wppractice1.presentation.MukundPostsScreen
import com.nolawiworkineh.wppractice1.ui.theme.WPpractice1Theme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WPpractice1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MukundPostsScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}