package com.rugid.core.ui.event

sealed interface EventType {

    object RefreshEvent : EventType
    object NoInternetEvent : EventType

}