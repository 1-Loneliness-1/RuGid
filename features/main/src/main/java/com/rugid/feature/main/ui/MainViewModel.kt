package com.rugid.feature.main.ui

import androidx.lifecycle.viewModelScope
import com.rugid.core.domain.result.DataResult
import com.rugid.core.ui.BaseViewModel
import com.rugid.core.ui.ScreenState
import com.rugid.feature.main.domain.interactor.GetMainScreenContentInteractor
import com.rugid.feature.main.domain.model.MainData
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainScreenContentInteractor: GetMainScreenContentInteractor
) : BaseViewModel<ScreenState<MainData>>() {

    override fun initStateOfStateFlow(): ScreenState<MainData> = ScreenState.Loading

    fun getDataForMainScreen() {

        viewModelScope.launch {
            mainScreenContentInteractor
                .getContentForMainScreen()
                .onStart { _state.value = ScreenState.Loading }
                .collect { result ->
                    _state.value = when (result) {
                        is DataResult.Success -> ScreenState.Content(result.data)
                        is DataResult.Error -> ScreenState.Error(result.error)
                    }
                }
        }
    }

}