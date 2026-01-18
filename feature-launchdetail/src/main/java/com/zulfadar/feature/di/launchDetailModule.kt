package com.zulfadar.feature.di

import com.zulfadar.feature.launchdetail.LaunchDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val launchDetailModule = module {
    viewModelOf(::LaunchDetailViewModel)
}