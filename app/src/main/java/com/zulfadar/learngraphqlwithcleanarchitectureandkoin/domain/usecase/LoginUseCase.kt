package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.usecase

import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.repository.LoginRepository

class LoginUseCase(
    private val loginRepository: LoginRepository
) {
    suspend fun invoke(email: String): String? {
        return loginRepository.login(email)
    }
}