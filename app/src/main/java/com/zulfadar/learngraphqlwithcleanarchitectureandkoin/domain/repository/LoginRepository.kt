package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.repository

interface LoginRepository {
    suspend fun login(email: String): String?
}