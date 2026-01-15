package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.data.remote.interceptor

import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.repository.TokenRepository
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(
    private val tokenRepository: TokenRepository
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .apply {
                tokenRepository.getToken()?.let { token ->
                    addHeader("Authorization", token)
                }
            }
            .build()
        return chain.proceed(request)
    }
}