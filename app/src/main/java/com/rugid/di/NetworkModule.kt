package com.rugid.di

import android.content.Context
import com.rugid.core.data.preferences.network.NetworkStatusProviderImpl
import com.rugid.core.domain.result.network.NetworkStatusProvider
import org.koin.dsl.module

val networkModule = module {

    single<NetworkStatusProvider> {
        NetworkStatusProviderImpl(get<Context>())
    }

}