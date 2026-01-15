package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.usecase

import androidx.paging.PagingData
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.model.Launch
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.repository.LaunchListRepository
import kotlinx.coroutines.flow.Flow

class GetLaunchListUseCase(
    private val launchListRepository: LaunchListRepository
) {
    operator fun invoke(): Flow<PagingData<Launch>> {
        return launchListRepository.getLaunchesWithPaging()
    }
}