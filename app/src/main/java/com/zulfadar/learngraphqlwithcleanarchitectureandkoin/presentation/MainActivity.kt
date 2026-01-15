package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.presentation.navigation.MainNavigation
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.presentation.ui.theme.LearnGraphQLwithCleanArchitectureAndKoinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearnGraphQLwithCleanArchitectureAndKoinTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    MainNavigation()
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    LearnGraphQLwithCleanArchitectureAndKoinTheme {
        MainScreen()
    }
}