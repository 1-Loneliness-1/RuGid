package com.rugid.core.model

sealed class DataState<out T> {

    object Loading : DataState<Nothing>()

    data class Success<T>(val data: T) : DataState<T>()

    data class Error(val error: Throwable) : DataState<Nothing>()

}