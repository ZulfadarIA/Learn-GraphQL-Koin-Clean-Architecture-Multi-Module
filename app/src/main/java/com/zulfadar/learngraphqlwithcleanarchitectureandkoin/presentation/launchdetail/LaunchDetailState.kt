package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.presentation.launchdetail

import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.model.LaunchDetail

sealed class LaunchDetailState {
    object Loading : LaunchDetailState()
    data class Success(val data: LaunchDetail) : LaunchDetailState()
    data class Error(val message: String) : LaunchDetailState()
}