package com.rugid.feature.main.ui.model

sealed class MainUiError {

    object NetworkError : MainUiError()
    data class UnknownError(val message: String) : MainUiError()

}