package com.rugid.feature.main.ui

sealed class MainUiError {

    object NetworkError : MainUiError()
    data class UnknownError(val message: String) : MainUiError()

}