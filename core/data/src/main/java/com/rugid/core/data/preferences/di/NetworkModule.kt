package com.rugid.core.data.preferences.di

import com.rugid.core.data.preferences.network.NetworkStatusProviderImpl
import com.rugid.core.domain.result.network.NetworkStatusProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networkModule = module {

    single<NetworkStatusProvider> {
        NetworkStatusProviderImpl(androidContext())
    }

}