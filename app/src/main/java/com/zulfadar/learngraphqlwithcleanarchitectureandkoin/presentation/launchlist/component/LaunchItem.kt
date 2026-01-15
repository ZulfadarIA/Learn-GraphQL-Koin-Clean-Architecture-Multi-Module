package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.presentation.launchlist.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.R
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.model.Launch

@Composable
fun LaunchItem(
    modifier: Modifier = Modifier,
    launch: Launch,
    onClick: (String) -> Unit
) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .clickable {
                onClick(launch.id)
            },
        colors = CardDefaults.cardColors(
            MaterialTheme.colorScheme.surfaceContainerLowest
        ),
        elevation = CardDefaults.cardElevation(2.dp),
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                modifier = Modifier.size(68.dp, 68.dp),
                model = launch.missionPatchUrl,
                contentDescription = "Mission patch",
                error =  painterResource(R.drawable.ic_placeholder),
                placeholder = painterResource(R.drawable.ic_placeholder),
            )
            Column(
                modifier = Modifier.padding(12.dp)
                    .weight(1f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = launch?.missionName ?: "",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = launch?.site ?: "",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}
//
//@Preview(showBackground = true)
//@Composable
//private fun LauncItemPrev() {
//    LearnGraphQLwithCleanArchitectureAndKoinTheme {
//        LaunchItem(
//            missionPatch = R.drawable.ic_placeholder,
//            missionName = "Mission Name",
//            site = "site",
//            onClick = {}
//        )
//    }
//}