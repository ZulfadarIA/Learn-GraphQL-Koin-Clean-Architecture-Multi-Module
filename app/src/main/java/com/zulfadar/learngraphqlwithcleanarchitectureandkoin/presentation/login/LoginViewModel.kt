package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.common.Result
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.idle)
    val loginState = _loginState.asStateFlow()

    fun login(email: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.isLoading

            when (val result = loginUseCase(email)) {
                is Result.Success -> {
                    _loginState.value = LoginState.Success
                }

                is Result.Error -> {
                    _loginState.value =
                        LoginState.Error(result.message)
                }
            }
        }
    }
}
