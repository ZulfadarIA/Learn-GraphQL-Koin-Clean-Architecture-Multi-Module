package com.zulfadar.core.usecase

import com.zulfadar.core.model.LaunchDetail
import com.zulfadar.core.repository.LaunchDetailRepository
import com.zulfadar.core.common.Result

class GetLaunchDetailByIdUseCase(
    private val repository: LaunchDetailRepository
) {
    suspend operator fun invoke(launchId: String): Result<LaunchDetail> {
        return repository.getLaunchDetailById(launchId)
    }
}