package com.rugid

import android.app.Application
import com.rugid.feature.main.di.mainScreenModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RugidApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RugidApplication)
            modules(mainScreenModule)
        }
    }
}