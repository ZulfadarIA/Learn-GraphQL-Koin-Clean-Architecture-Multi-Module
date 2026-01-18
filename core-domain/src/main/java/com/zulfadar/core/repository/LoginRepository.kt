package com.zulfadar.core.repository

import com.zulfadar.core.common.Result
import com.zulfadar.core.model.Login

interface LoginRepository {
    suspend fun login(
        email: String
    ): Result<Login>
}