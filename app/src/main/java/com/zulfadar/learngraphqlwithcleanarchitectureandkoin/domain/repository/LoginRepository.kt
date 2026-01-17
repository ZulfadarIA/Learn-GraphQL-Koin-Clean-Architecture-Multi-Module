package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.repository

import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.common.Result
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.model.Login

interface LoginRepository {
    suspend fun login(
        email: String
    ): Result<Login>
}