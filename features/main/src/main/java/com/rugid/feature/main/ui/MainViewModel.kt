package com.rugid.feature.main.ui

import androidx.lifecycle.viewModelScope
import com.rugid.core.domain.result.DataResult
import com.rugid.core.domain.result.DomainError
import com.rugid.core.domain.result.network.NetworkStatusProvider
import com.rugid.core.ui.BaseViewModel
import com.rugid.core.ui.ScreenState
import com.rugid.core.ui.event.EventProcessor
import com.rugid.core.ui.event.EventType
import com.rugid.feature.main.domain.interactor.GetMainScreenContentInteractor
import com.rugid.feature.main.domain.model.MainData
import com.rugid.feature.main.ui.model.MainUiEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainScreenContentInteractor: GetMainScreenContentInteractor,
    private val networkStatusProvider: NetworkStatusProvider,
    private val eventProcessor: EventProcessor<EventType>,
) : BaseViewModel<ScreenState<MainData>>() {

    init {
        observeEventProcessor()
    }

    private var currentLoadJob: Job? = null
    private val _events = MutableSharedFlow<MainUiEvent>()
    val events = _events.asSharedFlow()

    override fun initStateOfStateFlow(): ScreenState<MainData> = ScreenState.Loading

    fun onRefresh() {
        viewModelScope.launch {
            if (!networkStatusProvider.isConnected()) {
                eventProcessor.send(EventType.NoInternetEvent)
                return@launch
            }

            eventProcessor.send(EventType.RefreshEvent)
            getDataForMainScreen()
        }
    }

    fun getDataForMainScreen() {
        currentLoadJob?.cancel()

        currentLoadJob = viewModelScope.launch {
            mainScreenContentInteractor
                .getContentForMainScreen()
                .onStart {
                    _state.value = ScreenState.Loading
                }
                .collect { result ->
                    when (result) {
                        is DataResult.Success -> {
                            _state.value = ScreenState.Content(result.data)
                        }

                        is DataResult.Error -> {
                            when (result.error) {
                                is DomainError.Network -> {
                                    _events.emit(MainUiEvent.OpenNetworkError)
                                }
                                else -> {
                                    _state.value = ScreenState.Error(result.error)
                                }
                            }
                        }
                    }
                }
        }.also {
            it.invokeOnCompletion {
                currentLoadJob = null
            }
        }
    }

    private fun observeEventProcessor() {
        viewModelScope.launch {
            eventProcessor.events.collect { event ->
                when (event) {
                    is EventType.NoInternetEvent -> _events.emit(MainUiEvent.OpenNetworkError)
                    is EventType.RefreshEvent -> _events.emit(MainUiEvent.RefreshScreen)
                }
            }
        }
    }

}