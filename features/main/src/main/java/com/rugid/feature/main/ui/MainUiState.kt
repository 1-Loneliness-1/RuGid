package com.rugid.feature.main.ui

import com.rugid.feature.main.domain.model.MainData

sealed class MainUiState {

    object Loading : MainUiState()
    data class Content(val data: MainData) : MainUiState()
    data class Error(val error: MainUiError) : MainUiState()

}