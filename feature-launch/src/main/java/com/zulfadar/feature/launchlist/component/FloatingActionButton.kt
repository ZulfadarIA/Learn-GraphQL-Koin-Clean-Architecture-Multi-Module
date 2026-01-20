package com.zulfadar.feature.launchlist.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.HelpCenter
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zulfadar.core.ui.theme.LearnGraphQLwithCleanArchitectureAndKoinTheme


@Composable
fun CustomerServiceButton(
    modifier: Modifier = Modifier,
    onCsFabClick: () -> Unit,
) {
    ExtendedFloatingActionButton(
        modifier = modifier.padding(12.dp),
        onClick = onCsFabClick,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White,
        icon = {
            Icon(
                imageVector = Icons.Outlined.HelpCenter,
                contentDescription = "Extended floating action button.",
            )
        },
        text = {
            Text(text = "Customer Service")
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun CustomerServiceButtonPrev() {
    LearnGraphQLwithCleanArchitectureAndKoinTheme {
        CustomerServiceButton(
            onCsFabClick = {}
        )
    }
}