package com.zulfadar.learngraphqlwithcleanarchitectureandkoin

import android.app.Application
import com.zulfadar.core.di.domainModule
import com.zulfadar.core.di.networkModule
import com.zulfadar.di.launchListViewModelModule
import com.zulfadar.di.loginViewModelModule
import com.zulfadar.feature.di.launchDetailModule
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.di.appModule
import com.zulfadar.learngrapqlwithcleanarchitectureandkoin.core.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(
                appModule,
                networkModule,
                dataModule,
                domainModule,
                launchListViewModelModule,
                launchDetailModule,
                loginViewModelModule
            )
        }
    }
}