package com.rugid.feature.main.di

import com.rugid.feature.main.data.interactor.GetMainScreenContentInteractorImpl
import com.rugid.feature.main.data.repository.MainRepositoryImpl
import com.rugid.feature.main.domain.interactor.GetMainScreenContentInteractor
import com.rugid.feature.main.domain.repository.MainRepository
import com.rugid.feature.main.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val mainScreenModule = module {
    viewModelOf(::MainViewModel)

    factory<GetMainScreenContentInteractor> {
        GetMainScreenContentInteractorImpl(get())
    }

    factory<MainRepository> {
        MainRepositoryImpl()
    }
}