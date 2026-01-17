package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.data.repository

import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.data.datasource.LoginRemoteDataSource
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.common.Result
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.model.Login
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.repository.LoginRepository

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