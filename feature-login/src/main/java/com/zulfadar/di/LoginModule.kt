package com.zulfadar.di

import com.zulfadar.feature.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val loginViewModelModule = module {
    viewModelOf(::LoginViewModel)
}