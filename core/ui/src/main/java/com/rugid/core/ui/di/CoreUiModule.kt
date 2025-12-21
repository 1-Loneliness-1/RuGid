package com.rugid.core.ui.di

import com.rugid.core.ui.event.EventProcessor
import com.rugid.core.ui.event.EventType
import org.koin.dsl.module

val coreUiModule = module {
    factory {
        EventProcessor<EventType>()
    }
}