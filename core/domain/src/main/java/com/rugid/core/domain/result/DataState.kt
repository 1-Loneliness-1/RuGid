package com.rugid.core.domain.result

sealed interface DataState<out T> {

    data object Loading : DataState<Nothing>
    data class Success<T>(val data: T) : DataState<T>
    data class Error(val throwable: Throwable) : DataState<Nothing>

}