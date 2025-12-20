package com.rugid.feature.main.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.rugid.core.domain.result.DataResult
import com.rugid.core.domain.result.DomainError
import com.rugid.core.ui.BaseViewModel
import com.rugid.core.ui.ScreenState
import com.rugid.feature.main.domain.interactor.GetMainScreenContentInteractor
import com.rugid.feature.main.domain.model.MainData
import com.rugid.feature.main.ui.model.MainUiEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainScreenContentInteractor: GetMainScreenContentInteractor
) : BaseViewModel<ScreenState<MainData>>() {

    private var currentLoadJob: Job? = null
    private val _events = MutableSharedFlow<MainUiEvent>()
    val events = _events.asSharedFlow()

    override fun initStateOfStateFlow(): ScreenState<MainData> = ScreenState.Loading

    fun getDataForMainScreen() {
        currentLoadJob?.cancel()

        currentLoadJob = viewModelScope.launch {
            Log.d("fixMyState", "Начало получения данных: ${System.currentTimeMillis()}")
            mainScreenContentInteractor
                .getContentForMainScreen()
                .onStart {
                    Log.d("fixMyState", "Устанавливаю состояние загрузки")
                    _state.value = ScreenState.Loading
                }
                .collect { result ->
                    Log.d(
                        "fixMyState",
                        "Получен результат: $result. Время получения: ${System.currentTimeMillis()}"
                    )
                    when (result) {
                        is DataResult.Success -> {
                            Log.d("fixMyState", "Успех, данные получены без ошибок")
                            _state.value = ScreenState.Content(result.data)
                        }

                        is DataResult.Error -> {
                            Log.d(
                                "fixMyState",
                                "Ой, мы в блоке обработки ошибок! Посмотрим какой..."
                            )
                            when (result.error) {
                                is DomainError.Network -> {
                                    Log.d(
                                        "fixMyState",
                                        "Это же ошибка сети, похоже интернета нет..."
                                    )
                                    _state.value = ScreenState.Error(result.error)
                                    _events.emit(MainUiEvent.OpenNetworkError)
                                }

                                else -> {
                                    Log.d(
                                        "fixMyState",
                                        "Какая-то ошибка, для которой у нас не приготовлено никаких изменений экрана"
                                    )
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

}