package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.di

import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::MainViewModel)
}
