package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.presentation.login

sealed class LoginState<out T: Any> {
    object idle: LoginState<Nothing>()
    object isLoading: LoginState<Nothing>()
    data class Success<out T: Any>(val data: T) : LoginState<T>()
    data class Error(val errorMessage: String) : LoginState<Nothing>()
}