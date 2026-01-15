package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.repository

interface TokenRepository {

    fun getToken(): String?

    fun setToken(token: String)

    fun clearToken()
}
