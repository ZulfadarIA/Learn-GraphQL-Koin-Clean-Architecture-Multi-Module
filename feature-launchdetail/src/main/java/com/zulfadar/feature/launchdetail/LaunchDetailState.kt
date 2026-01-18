package com.zulfadar.feature.launchdetail

import com.zulfadar.core.model.LaunchDetail

sealed class LaunchDetailState {
    object Loading : LaunchDetailState()
    data class Success(val data: LaunchDetail) : LaunchDetailState()
    data class Error(val message: String) : LaunchDetailState()
}