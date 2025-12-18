package com.rugid.core.ui

import com.rugid.core.domain.result.DomainError

sealed interface ScreenState<out T> {
    object Loading : ScreenState<Nothing>
    data class Content<T>(val data: T) : ScreenState<T>
    data class Error(val error: DomainError) : ScreenState<Nothing>
}