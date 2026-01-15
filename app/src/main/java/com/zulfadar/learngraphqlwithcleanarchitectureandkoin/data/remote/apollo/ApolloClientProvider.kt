package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.data.remote.apollo

import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.okHttpClient
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.data.remote.interceptor.AuthorizationInterceptor
import kotlinx.coroutines.delay
import okhttp3.OkHttpClient

object ApolloClientProvider{

    fun createApolloClient(
        authorizationInterceptor: AuthorizationInterceptor
    ): ApolloClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(authorizationInterceptor)
            .build()

        val apolloClient = ApolloClient.Builder()
            .serverUrl("https://apollo-fullstack-tutorial.herokuapp.com/graphql")
            .webSocketServerUrl("wss://apollo-fullstack-tutorial.herokuapp.com/graphql")
            .webSocketReopenWhen { throwable, attempt ->
                Log.d("Apollo", "WebSocket got disconected, reopening after delay", throwable)
                delay(attempt * 1000)
                true
            }
            .okHttpClient(okHttpClient)
            .build()

        return apolloClient
    }
}