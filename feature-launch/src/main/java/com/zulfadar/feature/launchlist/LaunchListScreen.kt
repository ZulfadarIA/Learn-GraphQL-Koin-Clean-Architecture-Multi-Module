package com.zulfadar.feature.launchlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.zulfadar.core.model.Launch
import com.zulfadar.feature.launchlist.component.CustomerServiceButton
import com.zulfadar.feature.launchlist.component.ErrorMessage
import com.zulfadar.feature.launchlist.component.LaunchItem
import com.zulfadar.feature.launchlist.component.LoadingItem
import org.koin.androidx.compose.koinViewModel

//ditambahin root composable , statenya dipecah persection biar recomposenya gak banyak, ahnya disatu tempat/component
//kyk gini
@Composable
fun LaunchListRoot(
    onLaunchClick: (String) -> Unit,
    onCsFabClick: () -> Unit,
    viewModel: LaunchListViewModel = koinViewModel()
) {
    val uiState = viewModel.launches.collectAsLazyPagingItems()

    LaunchListScreen(
        uiState = uiState,
        onLaunchClick = onLaunchClick,
        onCsFabClick = onCsFabClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchListScreen(
    modifier: Modifier = Modifier,
    uiState: LazyPagingItems<Launch>,
    onLaunchClick: (String) -> Unit,
    onCsFabClick: () -> Unit,
) {
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
        },
        floatingActionButton = {
            CustomerServiceButton(
                onCsFabClick = onCsFabClick
            )
        }
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .fillMaxSize(),
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
        }
    }
}

@Composable
fun LaunchListContent(
    modifier: Modifier = Modifier,
    launchList: LazyPagingItems<Launch>,
    onLaunchClick: (String) -> Unit,
) {
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = modifier
            .padding(horizontal = 8.dp)
            .fillMaxSize()
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
        item {
            if (launchList.loadState.append is LoadState.Loading) {
                LoadingItem()
            }
        }
        item {
            val errorState = launchList.loadState.append as? LoadState.Error
            errorState?.let {
                ErrorMessage(text = it.error.message ?: "Failed to load more data")
            }
        }
    }
}