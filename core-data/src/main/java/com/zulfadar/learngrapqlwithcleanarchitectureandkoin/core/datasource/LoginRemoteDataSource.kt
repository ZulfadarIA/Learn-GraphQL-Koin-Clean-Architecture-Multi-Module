package com.zulfadar.learngrapqlwithcleanarchitectureandkoin.core.datasource

import com.apollographql.apollo.ApolloClient
import com.example.rocketreserver.LoginMutation

class LoginRemoteDataSource(
    private val apolloClient: ApolloClient
) {
    suspend fun login(email: String): String {
        val response = apolloClient.mutation(LoginMutation(email)).execute()

        if (response.hasErrors()) {
            throw Exception(response.errors?.first()?.message ?: "Login failed")
        } else {
            return response.data?.login?.token ?: throw Exception("Login failed")
        }
    }
}