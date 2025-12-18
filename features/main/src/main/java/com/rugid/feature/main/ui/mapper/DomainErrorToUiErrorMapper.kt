package com.rugid.feature.main.ui.mapper

import com.rugid.core.domain.result.DomainError
import com.rugid.feature.main.ui.MainUiError

fun Throwable.toUiError(): MainUiError =
    when (this) {
        is DomainError.Network -> MainUiError.NetworkError
        else -> MainUiError.UnknownError(message ?: "Unknown error")
    }