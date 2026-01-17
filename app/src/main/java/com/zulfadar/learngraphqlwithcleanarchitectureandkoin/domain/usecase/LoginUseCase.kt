package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.usecase

import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.common.Result
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.repository.LoginRepository
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.repository.TokenRepository

class LoginUseCase(
    private val loginRepository: LoginRepository,
    private val tokenRepository: TokenRepository
) {
    suspend operator fun invoke(email: String): Result<Unit> {
        return when (val result = loginRepository.login(email)) {
            is Result.Success -> {
                tokenRepository.setToken(result.data.token)
                Result.Success(Unit)
            }
            is Result.Error -> result
        }
    }
}