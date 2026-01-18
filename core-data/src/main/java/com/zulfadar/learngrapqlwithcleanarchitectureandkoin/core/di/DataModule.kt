package com.zulfadar.learngrapqlwithcleanarchitectureandkoin.core.di

import com.zulfadar.core.repository.LaunchDetailRepository
import com.zulfadar.core.repository.LaunchListRepository
import com.zulfadar.core.repository.LoginRepository
import com.zulfadar.core.repository.TokenRepository
import com.zulfadar.learngrapqlwithcleanarchitectureandkoin.core.datasource.LoginRemoteDataSource
import com.zulfadar.learngrapqlwithcleanarchitectureandkoin.core.repository.LaunchDetailRepositoryImpl
import com.zulfadar.learngrapqlwithcleanarchitectureandkoin.core.repository.LaunchListRepositoryImpl
import com.zulfadar.learngrapqlwithcleanarchitectureandkoin.core.repository.LoginRepositoryImpl
import com.zulfadar.learngrapqlwithcleanarchitectureandkoin.core.repository.TokenRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    single {
        LoginRemoteDataSource(
            apolloClient = get()
        )
    }
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
}