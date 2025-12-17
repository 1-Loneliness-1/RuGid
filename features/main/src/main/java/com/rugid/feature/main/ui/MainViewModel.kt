package com.rugid.feature.main.ui

import com.rugid.core.ui.BaseViewModel
import com.rugid.feature.main.domain.interactor.GetMainScreenContentInteractor

class MainViewModel(
    private val mainScreenContentInteractor: GetMainScreenContentInteractor
) : BaseViewModel<MainUiState>() {

    fun getDataForMainScreen() {
        getData {
            mainScreenContentInteractor.getContentForMainScreen()
        }
    }

}