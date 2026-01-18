package com.zulfadar.feature.launchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.zulfadar.core.usecase.GetLaunchPagingUseCase


class LaunchListViewModel(
   // private val getLaunchListUseCase: GetLaunchListUseCase,
    private val getLaunchPagingUseCase: GetLaunchPagingUseCase
) : ViewModel() {
//    private val _launchListUiState = MutableStateFlow<LaunchListState>(LaunchListState.Loading)
//    val launchListUistate = _launchListUiState.asStateFlow()
//
//    init {
//        loadLaunchList()
//    }
//
//    fun loadLaunchList() {
//        viewModelScope.launch {
//            _launchListUiState.value = LaunchListState.Loading
//            val result = getLaunchListUseCase.invoke()
//            when (result) {
//                is Result.Success -> _launchListUiState.value = LaunchListState.Success(result.)
//
//                is Result.Error ->
//                    _launchListUiState.value = LaunchListState.Error(
//                        result.message ?: "Something went wrong"
//                    )
//            }
//        }
//    }
    val launches = getLaunchPagingUseCase.invoke().cachedIn(viewModelScope)
}