package com.zulfadar.learngraphqlwithcleanarchitectureandkoin.di

import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.data.repository.LaunchDetailRepositoryImpl
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.data.repository.LaunchListRepositoryImpl
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.data.repository.LoginRepositoryImpl
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.data.repository.TokenRepositoryImpl
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.repository.LaunchDetailRepository
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.repository.LaunchListRepository
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.repository.LoginRepository
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.repository.TokenRepository
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.usecase.GetLaunchDetailByIdUseCase
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.usecase.GetLaunchListUseCase
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.usecase.GetLaunchPagingUseCase
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.domain.usecase.LoginUseCase
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.presentation.launchdetail.LaunchDetailViewModel
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.presentation.launchlist.LaunchListViewModel
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.presentation.login.LoginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {

    singleOf(::LaunchListRepositoryImpl) {
        bind<LaunchListRepository>()
    }

    singleOf(::LaunchDetailRepositoryImpl) {
        bind<LaunchDetailRepository>()
    }

    singleOf(::LoginRepositoryImpl) {
        bind<LoginRepository>()
    }

    single<TokenRepository> {
        TokenRepositoryImpl(
            context = androidContext()
        )
    }

    singleOf(::GetLaunchListUseCase)
    singleOf(::GetLaunchDetailByIdUseCase)
    singleOf(::GetLaunchPagingUseCase)
    singleOf(::LoginUseCase)

    viewModelOf(::LaunchListViewModel)
    viewModelOf(::LaunchDetailViewModel)
    viewModelOf(::LoginViewModel)
}
