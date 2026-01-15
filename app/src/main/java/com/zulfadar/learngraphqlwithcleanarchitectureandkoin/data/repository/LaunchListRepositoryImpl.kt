package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.apollographql.apollo.ApolloClient
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.data.paging.LaunchPagingSource
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.model.Launch
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.repository.LaunchListRepository
import kotlinx.coroutines.flow.Flow

class LaunchListRepositoryImpl(
    private val apolloClient: ApolloClient
): LaunchListRepository {
    override fun getLaunchesWithPaging(): Flow<PagingData<Launch>> {
        return Pager(
            config = PagingConfig(
                pageSize = 8,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                LaunchPagingSource(apolloClient)
            }
        ).flow
    }
    //    override suspend fun getLaunches(): Result<List<Launch>> {
//        return try {
//            val response = apolloClient.query(LaunchListQuery()).execute()
//            val launches = response
//                .data
//                ?.launches
//                ?.launches
//                ?.filterNotNull()
//                ?.map {
//                    it.toDomainLaunchList()
//                }
//                ?: emptyList()
//            Result.Success(launches)
//        } catch (e: Exception) {
//            Result.Error(e.message ?: "Failed to load launch list", e)
//        }
//    }
}