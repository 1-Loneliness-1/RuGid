package com.rugid.feature.main.di

import com.rugid.feature.main.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val mainScreenModule = module {
    viewModelOf(::MainViewModel)
}