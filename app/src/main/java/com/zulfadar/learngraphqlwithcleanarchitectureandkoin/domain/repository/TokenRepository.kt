package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.repository

interface TokenRepository {

//    fun init(context: Context)
    fun getToken(): String?

    fun setToken(token: String)

    fun clearToken()
}
