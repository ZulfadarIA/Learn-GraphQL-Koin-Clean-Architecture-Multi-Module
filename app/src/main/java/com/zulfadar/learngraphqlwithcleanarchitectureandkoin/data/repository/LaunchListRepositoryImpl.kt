package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.apollographql.apollo.ApolloClient
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.data.datasource.LaunchPagingSource
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.model.Launch
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.repository.LaunchListRepository
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