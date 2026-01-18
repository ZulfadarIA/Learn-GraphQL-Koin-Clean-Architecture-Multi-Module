package com.zulfadar.core.di

import com.apollographql.apollo.ApolloClient
import com.zulfadar.core.remote.apollo.ApolloClientProvider
import com.zulfadar.core.remote.interceptor.AuthorizationInterceptor
import org.koin.dsl.module

val networkModule = module {

    single {
        AuthorizationInterceptor(
            tokenRepository = get()
        )
    }

    single<ApolloClient> {
        ApolloClientProvider.createApolloClient(
            authorizationInterceptor = get()
        )
    }
}
