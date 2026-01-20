package com.zulfadar.feature_customerservice.customerservice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zulfadar.core.ui.theme.LearnGraphQLwithCleanArchitectureAndKoinTheme

@Composable
fun CustomerServiceScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Customer Service page"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CustomerServiceScreenPrev() {
    LearnGraphQLwithCleanArchitectureAndKoinTheme {
        CustomerServiceScreen()
    }
}