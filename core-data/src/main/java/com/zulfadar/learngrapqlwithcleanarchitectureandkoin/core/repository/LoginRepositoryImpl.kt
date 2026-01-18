package com.zulfadar.learngrapqlwithcleanarchitectureandkoin.core.repository

import com.zulfadar.core.common.Result
import com.zulfadar.core.model.Login
import com.zulfadar.core.repository.LoginRepository
import com.zulfadar.learngrapqlwithcleanarchitectureandkoin.core.datasource.LoginRemoteDataSource


class LoginRepositoryImpl(
    private val loginRemoteDataSource: LoginRemoteDataSource,
): LoginRepository {
    override suspend fun login(email: String): Result<Login> {
        return try {
            val token = loginRemoteDataSource.login(email)
            Result.Success(Login(token))
        } catch (e: Exception) {
            Result.Error(
                message = e.message ?: "Network error",
                throwable = e
            )
        }
    }
}