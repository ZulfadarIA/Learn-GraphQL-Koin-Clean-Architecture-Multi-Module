package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.data.repository

import com.apollographql.apollo.ApolloClient
import com.example.rocketreserver.LaunchDetailQuery
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.common.Result
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.model.LaunchDetail
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.repository.LaunchDetailRepository

class LaunchDetailRepositoryImpl(
    private val apolloClient: ApolloClient
) : LaunchDetailRepository {

    override suspend fun getLaunchDetailById(
        launchId: String
    ): Result<LaunchDetail> {
        return try {
            val response = apolloClient
                .query(LaunchDetailQuery(launchId))
                .execute()

            val data = response.data?.launch
                ?: return Result.Error("Launch not found")

            Result.Success(
                LaunchDetail(
                    id = data.id,
                    missionName = data.mission?.name.orEmpty(),
                    rocketName = data.rocket?.name.orEmpty(),
                    site = data.site.orEmpty(),
                    missionPatchUrl = data.mission?.missionPatch
                )
            )
        } catch (e: Exception) {
            Result.Error(e.message ?: "Network error")
        }
    }
}
