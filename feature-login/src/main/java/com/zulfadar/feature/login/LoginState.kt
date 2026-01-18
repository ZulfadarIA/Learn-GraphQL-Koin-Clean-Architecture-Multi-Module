package com.zulfadar.feature.login

sealed class LoginState {
    object idle: LoginState()
    object isLoading: LoginState()
    object Success: LoginState()
    data class Error(val errorMessage: String?) : LoginState()
}