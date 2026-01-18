package com.zulfadar.di

import com.zulfadar.feature.launchlist.LaunchListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val launchListViewModelModule = module {
    viewModelOf(::LaunchListViewModel)
}