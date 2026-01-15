package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.presentation.login

import androidx.lifecycle.ViewModel
import com.example.rocketreserver.LoginMutation
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel(
    private val loginUseCase: LoginUseCase
): ViewModel() {
    private val _loginState = MutableStateFlow<LoginState<LoginMutation.Login>>(LoginState.isLoading)
    val loginState = _loginState.asStateFlow()

    fun login() {

    }
}