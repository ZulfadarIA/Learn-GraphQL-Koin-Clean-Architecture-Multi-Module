package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.di

import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.data.remote.apollo.ApolloClientProvider
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.data.remote.interceptor.AuthorizationInterceptor
import org.koin.dsl.module

val networkModule = module {

    single<AuthorizationInterceptor> {
        AuthorizationInterceptor(
            tokenRepository = get()
        )
    }

    single {
        ApolloClientProvider.createApolloClient(
            authorizationInterceptor = get()
        )
    }
}
