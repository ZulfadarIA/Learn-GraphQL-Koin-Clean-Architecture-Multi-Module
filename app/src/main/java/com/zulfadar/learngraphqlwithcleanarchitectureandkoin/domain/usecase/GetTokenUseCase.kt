package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.usecase

import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.repository.TokenRepository

class GetTokenUseCase(
    private val tokenRepository: TokenRepository
) {
    suspend fun invoke(): String? {
        return tokenRepository.getToken()
    }
}