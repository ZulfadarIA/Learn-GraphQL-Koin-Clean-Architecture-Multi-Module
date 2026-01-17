package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.usecase

import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.common.Result
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.model.Booking
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.repository.LaunchDetailRepository
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.repository.TokenRepository

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