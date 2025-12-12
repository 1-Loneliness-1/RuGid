package com.rugid.core.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rugid.core.model.DataState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<T> : ViewModel() {

    private val _state = MutableStateFlow<DataState<T>>(DataState.Loading)
    val state: StateFlow<DataState<T>> = _state

    protected fun getData(
        block: suspend () -> T
    ) {
        viewModelScope.launch {
            _state.value = DataState.Loading

            try {
                val result = block()
                _state.value = DataState.Success(result)
            } catch (e: Throwable) {
                _state.value = DataState.Error(e)
            }
        }
    }

}