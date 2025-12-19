package com.rugid.core.domain.result

sealed class DataResult<out T> {
    data class Success<T>(val data: T) : DataResult<T>()
    data class Error(val error: DomainError) : DataResult<Nothing>()
}