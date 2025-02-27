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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nolawiworkineh.wppractice1.presentation.PostDetailScreen
import com.nolawiworkineh.wppractice1.presentation.PostsScreen
import com.nolawiworkineh.wppractice1.ui.theme.WPpractice1Theme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
// this basically tells hilt that this is the entry point and to inject dependencies into
// this class, without this the app will crash at runtime
// the app class creates the application graph but does not inject the dependencies in activities
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WPpractice1Theme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "posts",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("posts") {
                            PostsScreen(
                                onPostClick = { postId ->
                                    navController.navigate("post_detail/$postId")
                                }
                            )
                        }

                        composable(
                            route = "post_detail/{postId}",
                            arguments = listOf(
                                navArgument("postId") {
                                    type = NavType.IntType
                                }
                            )
                        ) { backStackEntry ->
                            val postId = backStackEntry.arguments?.getInt("postId") ?: -1
                            PostDetailScreen(postId = postId)
                        }
                    }
                }
            }
        }
    }
}