package com.rugid.core.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rugid.core.domain.result.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<T> : ViewModel() {

    private val _state = MutableStateFlow<T> by lazy {
        MutableStateFlow(initStateOfStateFlow())
    }
    val state: StateFlow<T> = _state

    protected abstract fun initStateOfStateFlow()

    protected fun getData(
        block: () -> Flow<DataResult<T>>
    ) {
        viewModelScope.launch {
            _state.value = DataState.Loading

            block().collect { result ->
                _state.value = when (result) {
                    is DataResult.Success -> DataState.Success(result.data)
                    is DataResult.Error -> DataState.Error(result.error)
                }
            }
        }
    }

}