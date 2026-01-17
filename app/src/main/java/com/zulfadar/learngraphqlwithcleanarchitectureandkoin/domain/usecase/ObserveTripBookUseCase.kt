package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.usecase

import com.apollographql.apollo.ApolloClient
import com.example.rocketreserver.TripsBookedSubscription
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ObserveTripBookUseCase(
    private val apolloClient: ApolloClient
) {
    fun execute(): Flow<String> {
        return apolloClient
            .subscription(TripsBookedSubscription())
            .toFlow()
            .map { response ->
                when (response.data?.tripsBooked) {
                    null -> "Subscription error"
                    -1 -> "Trip cancelled"
                    else -> "Trip booked! 🚀"
                }
            }
    }
}