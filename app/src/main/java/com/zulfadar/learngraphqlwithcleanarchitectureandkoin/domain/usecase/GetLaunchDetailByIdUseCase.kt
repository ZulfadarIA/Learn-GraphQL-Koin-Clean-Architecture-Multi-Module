package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.usecase

import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.model.LaunchDetail
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.repository.LaunchDetailRepository
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.common.Result

class GetLaunchDetailByIdUseCase(
    private val repository: LaunchDetailRepository
) {
    suspend operator fun invoke(launchId: String): Result<LaunchDetail> {
        return repository.getLaunchDetailById(launchId)
    }
}