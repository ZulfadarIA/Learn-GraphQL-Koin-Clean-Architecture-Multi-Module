package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zulfadar.core.usecase.ObserveTripBookUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MainViewModel(
    observeTripBookUseCase: ObserveTripBookUseCase
): ViewModel() {
    val snackbarMessage: StateFlow<String?> = observeTripBookUseCase.execute()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_00),
            initialValue = null
        )
}