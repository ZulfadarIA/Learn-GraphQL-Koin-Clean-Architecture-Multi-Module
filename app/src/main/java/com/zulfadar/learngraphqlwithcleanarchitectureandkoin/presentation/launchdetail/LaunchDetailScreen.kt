package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.presentation.launchdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.R
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.model.LaunchDetail
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.presentation.common.component.ErrorMessage
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.presentation.common.component.LoadingItem
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchDetailScreen(
    modifier: Modifier = Modifier,
    launchId: String,
    navigateToLogin: () -> Unit
) {
    val viewModel: LaunchDetailViewModel = koinViewModel()
    val launchDetailState by viewModel.uiState.collectAsStateWithLifecycle()
//    val bookState by viewModel.bookState.collectAsStateWithLifecycle()

    LaunchedEffect(launchId) {
        if (viewModel.uiState.value !is LaunchDetailState.Success) {
            viewModel.loadLaunchDetail(launchId)
        }
    }

//    LaunchedEffect(bookState.requireLogin) {
//        if (bookState.requireLogin) {
//            navigateToLogin()
//            viewModel.consumeLoginEvent()
//        }
//    }

    Scaffold(
        topBar =  {
            TopAppBar(
                title = {
                    Text(
                        text = "Launch Detail",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
            )
        }
    ) {
        Column(
            modifier = modifier.padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when(launchDetailState) {
                is LaunchDetailState.Loading -> {
                    LoadingItem()
                }
                is LaunchDetailState.Error -> {
                    ErrorMessage(
                        text = (launchDetailState as LaunchDetailState.Error).message
                    )
                }
                is LaunchDetailState.Success -> {
                    val launchDetailData = (launchDetailState as LaunchDetailState.Success).data
                    LaunchDetailContent(
                        modifier = Modifier,
                        launchDetailData = launchDetailData,
//                        bookingLoadng = bookState.loading,
//                        isBooked = bookState.isBooked,
                        navigateToLogin = {
//                            viewModel.onBookButtonClick(
//                                launchDetailData.launch?.id.orEmpty()
//                            )
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun LaunchDetailContent(
    modifier: Modifier = Modifier,
    launchDetailData: LaunchDetail,
    navigateToLogin: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Mission patch
            AsyncImage(
                modifier = Modifier.size(160.dp, 160.dp),
                model = launchDetailData.missionPatchUrl,
                placeholder = painterResource(R.drawable.ic_placeholder),
                error = painterResource(R.drawable.ic_placeholder),
                contentDescription = "Mission patch"
            )

            Spacer(modifier = Modifier.size(16.dp))

            Column {
                // Mission name
                Text(
                    style = MaterialTheme.typography.headlineMedium,
                    text = launchDetailData?.missionName ?: ""
                )

                // Rocket name
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    style = MaterialTheme.typography.headlineSmall,
                    text = launchDetailData?.site.let { "🚀 $it" } ?: "",
                )

                // Site
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    style = MaterialTheme.typography.titleMedium,
                    text = launchDetailData?.site ?: "",
                )
            }
        }

        // Book button
        Button(
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth(),
//            enabled = !bookingLoadng,
            onClick = navigateToLogin,
        ) {
//            if (bookingLoadng) {
//                SmallLoading()
//            } else {
//                Text(text = if (!isBooked) "Book now" else "Cancel booking")
//            }
            Text(text = "Book now")
        }
    }
}

