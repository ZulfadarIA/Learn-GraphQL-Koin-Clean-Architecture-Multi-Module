package com.zulfadar.learngrapqlwithcleanarchitectureandkoin.core.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Mutation
import com.example.rocketreserver.BookTripMutation
import com.example.rocketreserver.CancelTripMutation
import com.example.rocketreserver.LaunchDetailQuery
import com.example.rocketreserver.TripsBookedSubscription
import com.zulfadar.core.common.Result
import com.zulfadar.core.model.Booking
import com.zulfadar.core.model.LaunchDetail
import com.zulfadar.core.repository.LaunchDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

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

    override fun observeTripsBooked(): Flow<Int?> {
        return apolloClient
            .subscription(TripsBookedSubscription())
            .toFlow()
            .map { it.data?.tripsBooked }
    }

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
