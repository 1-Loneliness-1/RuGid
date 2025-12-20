package com.rugid.feature.main.di

import com.rugid.feature.main.data.interactor.GetMainScreenContentInteractorImpl
import com.rugid.feature.main.data.mapper.ArticleDtoMapper
import com.rugid.feature.main.data.mapper.ExcursionDtoMapper
import com.rugid.feature.main.data.mapper.PlaceDtoMapper
import com.rugid.feature.main.data.mapper.VideoDtoMapper
import com.rugid.feature.main.data.repository.MainRepositoryImpl
import com.rugid.feature.main.domain.interactor.GetMainScreenContentInteractor
import com.rugid.feature.main.domain.repository.MainRepository
import com.rugid.feature.main.ui.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val mainScreenModule = module {
    viewModel { MainViewModel(get()) }

    factory<GetMainScreenContentInteractor> {
        GetMainScreenContentInteractorImpl(get(), get())
    }

    factory<MainRepository> {
        MainRepositoryImpl(get(), get(), get(), get())
    }

    single {
        VideoDtoMapper()
    }

    single {
        ArticleDtoMapper()
    }

    single {
        PlaceDtoMapper()
    }

    single {
        ExcursionDtoMapper()
    }
}