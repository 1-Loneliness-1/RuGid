package com.rugid.core.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rugid.core.model.DataState
import kotlinx.coroutines.launch

abstract class BaseViewModel<T> : ViewModel() {

    private val _state = MutableLiveData<DataState<T>>(DataState.Loading)
    fun getStateLiveData(): LiveData<DataState<T>> =
        _state

    protected fun getData(
        block: suspend () -> T
    ) {
        viewModelScope.launch {
            _state.postValue(DataState.Loading)

            try {
                val result = block()
                _state.postValue(DataState.Success(result))
            } catch (e: Throwable) {
                _state.postValue(DataState.Error(e))
            }
        }
    }

}