package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.data.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Mutation
import com.example.rocketreserver.BookTripMutation
import com.example.rocketreserver.CancelTripMutation
import com.example.rocketreserver.LaunchDetailQuery
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.common.Result
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.model.Booking
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
                    missionPatchUrl = data.mission?.missionPatch,
                    isBooked = data.isBooked
                )
            )
        } catch (e: Exception) {
            Result.Error(e.message ?: "Network error")
        }
    }

    override suspend fun booking(launchId: String): Result<Booking> = executeBooking(BookTripMutation(listOf(launchId)))


    override suspend fun cancelBooking(launchId: String): Result<Booking> = executeBooking(CancelTripMutation(launchId))

    private suspend fun executeBooking(mutation: Mutation<*>): Result<Booking> {
        return try {
            val response = apolloClient.mutation(mutation).execute()

            if (response.hasErrors()) {
                return Result.Error(response.errors?.first()?.message)
            }

            Result.Success(
                Booking(
                    success = true,
                    message = "Success"
                )
            )
        } catch (e: Exception) {
            Result.Error("Network error", e)
        }
    }
}
