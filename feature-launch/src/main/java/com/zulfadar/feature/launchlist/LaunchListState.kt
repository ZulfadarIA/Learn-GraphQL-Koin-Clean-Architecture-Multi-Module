package com.zulfadar.feature.launchlist

import com.zulfadar.core.model.Launch

sealed class LaunchListState {
    object Loading : LaunchListState()
    data class Success(val data: List<Launch>) : LaunchListState()
    data class Error(val message: String) : LaunchListState()
}