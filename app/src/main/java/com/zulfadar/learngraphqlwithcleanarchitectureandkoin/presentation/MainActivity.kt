package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.presentation.navigation.MainNavigation
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.presentation.ui.theme.LearnGraphQLwithCleanArchitectureAndKoinTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearnGraphQLwithCleanArchitectureAndKoinTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                val message by viewModel.snackbarMessage.collectAsStateWithLifecycle()

                LaunchedEffect(message) {
                    message?.let {
                        snackbarHostState.showSnackbar(
                            message = it,
                            withDismissAction = true
                        )
                    }
                }
                Scaffold(
                    snackbarHost = { SnackbarHost(snackbarHostState) }
                ) {
                    MainScreen(modifier = Modifier.padding(it))
                }
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