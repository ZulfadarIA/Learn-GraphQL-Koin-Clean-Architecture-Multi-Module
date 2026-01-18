package com.zulfadar.core.repository

interface TokenRepository {

//    fun init(context: Context)
    fun getToken(): String?

    fun setToken(token: String)

    fun clearToken()
}
