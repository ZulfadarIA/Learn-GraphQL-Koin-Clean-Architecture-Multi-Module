package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.presentation.launchlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.model.Launch
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.presentation.common.component.ErrorMessage
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.presentation.common.component.LoadingItem
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.presentation.launchlist.component.LaunchItem
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchListScreen(
    modifier: Modifier = Modifier,
    onLaunchClick: (String) -> Unit,
    viewModel: LaunchListViewModel = koinViewModel()
) {
    //val uiState by viewModel.launchListUistate.collectAsStateWithLifecycle()
    val uiState = viewModel.launches.collectAsLazyPagingItems()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Launch List",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            )
        }
    ) {
        Column(
            modifier = modifier.padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (val state = uiState.loadState.refresh) {
                is LoadState.Loading -> {
                    LoadingItem()
                }
                is LoadState.Error -> {
                    ErrorMessage(
                        text = state.error.message ?: "Something went wrong"
                    )
                }
                else -> {
                    LaunchListContent(
                        launchList = uiState,
                        onLaunchClick = onLaunchClick
                    )
                }
            }
//            when(uiState) {
//                is LaunchListState.Loading -> {
//                    LoadingItem()
//                }
//                is LaunchListState.Error -> {
//                    ErrorMessage(
//                        text = (uiState as LaunchListState.Error).message
//                    )
//                }
//                is LaunchListState.Success ->  {
//                    val launchList = (uiState as LaunchListState.Success).data
//                    LaunchListContent(
//                        launchList = launchList,
//                        onLaunchClick = onLaunchClick,
//                    )
//                }
//            }
        }
    }
}

@Composable
fun LaunchListContent(
    modifier: Modifier = Modifier,
    launchList: androidx.paging.compose.LazyPagingItems<Launch>,
    onLaunchClick: (String) -> Unit,
) {
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = modifier
    ) {
        items(launchList.itemCount) {index ->
            val launch = launchList[index]
            if (launch != null) {
                LaunchItem(
                    launch = launch,
                    onClick = onLaunchClick,
                )
            }
        }
        // ⏳ Loading pagination
        item {
            if (launchList.loadState.append is androidx.paging.LoadState.Loading) {
                LoadingItem()
            }
        }

        // ❌ Error pagination
        item {
            val errorState = launchList.loadState.append as? androidx.paging.LoadState.Error
            errorState?.let {
                ErrorMessage(text = it.error.message ?: "Failed to load more data")
            }
        }
    }
}