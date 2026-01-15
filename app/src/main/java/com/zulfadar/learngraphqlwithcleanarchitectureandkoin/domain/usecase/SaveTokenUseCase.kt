package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.usecase

import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.repository.TokenRepository

class SaveTokenUseCase(
    private val tokenRepository: TokenRepository
) {
    fun invoke(token: String) {
        tokenRepository.setToken(token)
    }
}