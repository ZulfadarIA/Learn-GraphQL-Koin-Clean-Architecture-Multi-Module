package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.presentation.launchlist

import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.model.Launch

sealed class LaunchListState {
    object Loading : LaunchListState()
    data class Success(val data: List<Launch>) : LaunchListState()
    data class Error(val message: String) : LaunchListState()
}