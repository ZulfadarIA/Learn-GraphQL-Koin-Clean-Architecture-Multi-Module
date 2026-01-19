package com.zulfadar.core.di

import com.zulfadar.core.usecase.BookingUseCase
import com.zulfadar.core.usecase.GetLaunchDetailByIdUseCase
import com.zulfadar.core.usecase.GetLaunchListUseCase
import com.zulfadar.core.usecase.LoginUseCase
import com.zulfadar.core.usecase.ObserveTripBookUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::GetLaunchListUseCase)
    singleOf(::GetLaunchDetailByIdUseCase)
    singleOf(::BookingUseCase)
    singleOf(::ObserveTripBookUseCase)
    single {
        LoginUseCase(
            loginRepository = get(),
            tokenRepository = get()
        )
    }
}