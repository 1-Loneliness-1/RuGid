package com.rugid.feature.main.ui.model

sealed interface MainUiEvent {

    object OpenNetworkError : MainUiEvent
    object RefreshScreen : MainUiEvent

}