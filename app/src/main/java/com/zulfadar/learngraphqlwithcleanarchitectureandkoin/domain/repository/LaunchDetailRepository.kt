package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.repository

import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.common.Result
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.model.LaunchDetail

interface LaunchDetailRepository {
    suspend fun getLaunchDetailById(
        launchId: String
    ): Result<LaunchDetail>
}