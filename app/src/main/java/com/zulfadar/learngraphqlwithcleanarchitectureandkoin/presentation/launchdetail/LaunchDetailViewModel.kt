package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.presentation.launchdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.common.Result
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.usecase.GetLaunchDetailByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LaunchDetailViewModel(
    private val getLaunchDetailByIdUseCase: GetLaunchDetailByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<LaunchDetailState>(LaunchDetailState.Loading)
    val uiState = _uiState.asStateFlow()

    fun loadLaunchDetail(launchId: String) {
        viewModelScope.launch {
            _uiState.value = LaunchDetailState.Loading
            val result = getLaunchDetailByIdUseCase(launchId)
            when (result) {
                is Result.Success -> {
                    _uiState.value = LaunchDetailState.Success(result.data)
                }
                is Result.Error -> {
                    _uiState.value = LaunchDetailState.Error(
                        result.message ?: "Something went wrong"
                    )
                }
            }
        }
    }
}
