package com.zulfadar.core.usecase

import androidx.paging.PagingData
import com.zulfadar.core.model.Launch
import com.zulfadar.core.repository.LaunchListRepository
import kotlinx.coroutines.flow.Flow

class GetLaunchListUseCase(
    private val launchListRepository: LaunchListRepository
) {
    operator fun invoke(): Flow<PagingData<Launch>> {
        return launchListRepository.getLaunchesWithPaging()
    }
}