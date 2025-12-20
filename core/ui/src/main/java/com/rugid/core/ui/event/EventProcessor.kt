package com.rugid.core.ui.event

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class EventProcessor<T>() {

    private val _events = MutableSharedFlow<T>(extraBufferCapacity = 1)
    val events: SharedFlow<T> = _events

    suspend fun send(event: T) {
        _events.emit(event)
    }

}