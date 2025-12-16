package com.rugid.feature.main.ui

import com.rugid.core.ui.BaseViewModel
import com.rugid.feature.main.domain.interactor.GetMainScreenContentInteractor
import com.rugid.feature.main.domain.model.MainUiState

class MainViewModel(
    private val mainScreenContentInteractor: GetMainScreenContentInteractor
) : BaseViewModel<MainUiState>() {

}