package com.zulfadar.core.usecase

import com.zulfadar.core.common.Result
import com.zulfadar.core.repository.LoginRepository
import com.zulfadar.core.repository.TokenRepository

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