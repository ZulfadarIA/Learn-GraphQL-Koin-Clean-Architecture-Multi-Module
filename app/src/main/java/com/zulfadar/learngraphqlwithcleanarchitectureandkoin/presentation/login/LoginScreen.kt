package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.presentation.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.presentation.launchdetail.component.SmallLoading
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.presentation.ui.theme.LearnGraphQLwithCleanArchitectureAndKoinTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit
) {
    val viewModel: LoginViewModel = koinViewModel()
    val loginUiState by viewModel.loginState.collectAsStateWithLifecycle()
    val loading = loginUiState is LoginState.isLoading

    LaunchedEffect(loginUiState) {
        if (loginUiState is LoginState.Success) {
            navigateBack()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Title
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
            text = "Login"
        )

        // Email
        var email by remember { mutableStateOf("") }
        OutlinedTextField(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            value = email,
            onValueChange = { email = it }
        )

        // Submit button
        Button(
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth(),
            onClick = {
                viewModel.login(email)
            }
        ) {
            if (loading) {
                SmallLoading()
            } else {
                Text(text = "Submit")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPrev() {
    LearnGraphQLwithCleanArchitectureAndKoinTheme {
        LoginScreen(navigateBack = {})
    }
}

