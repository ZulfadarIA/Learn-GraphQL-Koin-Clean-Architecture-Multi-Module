package com.zulfadar.core.repository

import androidx.paging.PagingData
import com.zulfadar.core.model.Launch
import kotlinx.coroutines.flow.Flow

interface LaunchListRepository {
//    suspend fun getLaunches(): Result<List<Launch>>
    fun getLaunchesWithPaging(): Flow<PagingData<Launch>>
}