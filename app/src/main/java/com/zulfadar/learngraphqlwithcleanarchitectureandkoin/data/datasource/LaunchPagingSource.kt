package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.apollographql.apollo.ApolloClient
import com.example.rocketreserver.LaunchListQuery
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.data.mapper.toDomainLaunchList
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.model.Launch

class LaunchPagingSource(
    private val apolloClient: ApolloClient
) : PagingSource<Int, Launch>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Launch> {
        return try {
            val page = params.key ?: 0
            val response = apolloClient.query(LaunchListQuery()).execute()

            val launches = response.data?.launches?.launches
                ?.filterNotNull()
                ?.map { it.toDomainLaunchList() }
                ?: emptyList()

            val from = page * params.loadSize
            val to = minOf(from + params.loadSize, launches.size)

            val pageData = if (from < to) launches.subList(from, to) else emptyList()

            LoadResult.Page(
                data = pageData,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (to < launches.size) page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Launch>): Int? =
        state.anchorPosition?.let { it / state.config.pageSize }
}