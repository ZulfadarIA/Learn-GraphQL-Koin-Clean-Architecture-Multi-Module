package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.repository

import androidx.paging.PagingData
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.model.Launch
import kotlinx.coroutines.flow.Flow

interface LaunchListRepository {
//    suspend fun getLaunches(): Result<List<Launch>>
    fun getLaunchesWithPaging(): Flow<PagingData<Launch>>
}