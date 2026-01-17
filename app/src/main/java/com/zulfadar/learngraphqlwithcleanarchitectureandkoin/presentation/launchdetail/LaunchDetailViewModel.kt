package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.presentation.launchdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.common.Result
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.usecase.BookingUseCase
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.usecase.GetLaunchDetailByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LaunchDetailViewModel(
    private val getLaunchDetailByIdUseCase: GetLaunchDetailByIdUseCase,
    private val bookingUseCase: BookingUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<LaunchDetailState>(LaunchDetailState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _bookingState = MutableStateFlow(BookingState())
    val bookingState = _bookingState.asStateFlow()

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

    fun onBookingClick(launchId: String, isBooked: Boolean) {
        viewModelScope.launch {
            _bookingState.value = BookingState(loading = true)

            when (val result = bookingUseCase(launchId, isBooked)) {
                is Result.Success -> {
                    _bookingState.value = BookingState(
                        loading = false,
                        isBooked = !isBooked
                    )
                }
                is Result.Error -> {
                    if (result.message == "REQUIRE_LOGIN") {
                        _bookingState.value = BookingState(requireLogin = true)
                    } else {
                        _bookingState.value = BookingState(
                            error = result.message
                        )
                    }
                }
            }
        }
    }

    fun consumeLoginEvent() {
        _bookingState.value = _bookingState.value.copy(requireLogin = false)
    }
}
