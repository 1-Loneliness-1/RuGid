package com.rugid.core.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

abstract class BaseViewModel<T> : ViewModel() {

    protected val _state: MutableStateFlow<T> by lazy {
        MutableStateFlow(initStateOfStateFlow())
    }
    val state: StateFlow<T> = _state

    protected abstract fun initStateOfStateFlow(): T

    protected fun <T> getData(
        flow: Flow<T>,
        onEach: (T) -> Unit,
        onError: (Throwable) -> Unit = {},
    ) {
        viewModelScope.launch {
            flow
                .catch { e ->
                    if (e is CancellationException) throw e
                    onError(e)
                }
                .collect { onEach(it) }
        }
    }

}