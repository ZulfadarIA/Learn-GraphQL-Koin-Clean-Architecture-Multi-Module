package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.data.repository

import com.apollographql.apollo.ApolloClient
import com.example.rocketreserver.LoginMutation
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.repository.LoginRepository

class LoginRepositoryImpl(
    private val apolloClient: ApolloClient
): LoginRepository {
    override suspend fun login(email: String): String? {
        val response = apolloClient.mutation(LoginMutation(email)).execute()
        val result = response.data?.login
        return null
    }
}