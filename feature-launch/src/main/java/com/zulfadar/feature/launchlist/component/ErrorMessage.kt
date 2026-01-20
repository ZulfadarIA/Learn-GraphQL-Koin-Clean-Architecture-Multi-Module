package com.zulfadar.feature.launchlist.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.zulfadar.core.ui.theme.LearnGraphQLwithCleanArchitectureAndKoinTheme

@Composable
fun ErrorMessage(text: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorMessagePrev() {
    LearnGraphQLwithCleanArchitectureAndKoinTheme { 
        ErrorMessage(
            text = "error"
        )
    }
}