package com.zulfadar.learngrapqlwithcleanarchitectureandkoin.core.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.apollographql.apollo.ApolloClient
import com.zulfadar.learngrapqlwithcleanarchitectureandkoin.core.datasource.LaunchPagingSource
import com.zulfadar.core.model.Launch
import com.zulfadar.core.repository.LaunchListRepository
import kotlinx.coroutines.flow.Flow

class LaunchListRepositoryImpl(
    private val apolloClient: ApolloClient
): LaunchListRepository {
    override fun getLaunchesWithPaging(): Flow<PagingData<Launch>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                prefetchDistance = 1,
                enablePlaceholders = false,
                initialLoadSize = 5,
            ),
            pagingSourceFactory = {
                LaunchPagingSource(apolloClient)
            }
        ).flow
    }
}