package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Optional
import com.example.rocketreserver.LaunchListQuery
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.data.mapper.toDomainLaunchList
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.model.Launch

class LaunchPagingSource(
    private val apolloClient: ApolloClient
) : PagingSource<String, Launch>() {
    override suspend fun load(params: LoadParams<String>): LoadResult<String, Launch> {
        return try {
            var cursor = params.key
            val response = apolloClient.query(LaunchListQuery(Optional.presentIfNotNull(cursor))).execute()
            val launches = response
                .data
                ?.launches
                ?.launches
                ?.filterNotNull()
                ?.map {
                    it.toDomainLaunchList()
                }
                ?: emptyList()
            val nextKey = response.data?.launches?.cursor
            val hasMore = response.data?.launches?.hasMore ?: false
            if (response.hasErrors()) {
                LoadResult.Error(
                    RuntimeException(
                        response.errors?.firstOrNull()?.message
                            ?: "GraphQL error"
                    )
                )
            } else {
                LoadResult.Page(
                    data = launches ?: emptyList(),
                    prevKey = null,
                    nextKey = if (hasMore == true) nextKey else null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, Launch>): String? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.nextKey
        }
    }
}