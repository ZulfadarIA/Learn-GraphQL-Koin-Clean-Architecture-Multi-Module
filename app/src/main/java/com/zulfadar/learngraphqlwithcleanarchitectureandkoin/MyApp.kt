package com.zulfadar.learngraphqlwithcleanarchitectureandkoin

import android.app.Application
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.di.appModule
import com.zulfadar.learngraphqlwithcleanarchitectureandkoin.di.networkModule
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
                networkModule
            )
        }
    }
}