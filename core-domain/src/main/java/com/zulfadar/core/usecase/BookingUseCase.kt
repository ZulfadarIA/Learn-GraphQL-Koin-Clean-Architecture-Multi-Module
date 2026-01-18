package com.zulfadar.core.usecase

import com.zulfadar.core.common.Result
import com.zulfadar.core.model.Booking
import com.zulfadar.core.repository.LaunchDetailRepository
import com.zulfadar.core.repository.TokenRepository

class BookingUseCase(
    private val launchDetailRepository: LaunchDetailRepository,
    private val tokenRepository: TokenRepository
) {
    suspend operator fun invoke(
        launchId: String,
        isBooked: Boolean
    ): Result<Booking> {

        val token = tokenRepository.getToken()
            ?: return Result.Error("REQUIRE_LOGIN")

        return if (isBooked) {
            launchDetailRepository.cancelBooking(launchId)
        } else {
            launchDetailRepository.booking(launchId)
        }
    }
}