package com.zulfadar.core.usecase

import com.zulfadar.core.repository.LaunchDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ObserveTripBookUseCase(
    private val launchDetailRepository: LaunchDetailRepository
) {
    fun execute(): Flow<String> {
        return launchDetailRepository.observeTripsBooked().map { status ->
            when (status) {
                null -> "Subscription error"
                -1 -> "Trip cancelled"
                else -> "Trip booked! 🚀"
            }
        }
    }
}