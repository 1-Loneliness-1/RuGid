package com.rugid.core.domain.result.mapper

import com.rugid.core.domain.result.DomainError
import java.io.IOException

fun Throwable.toDomainError(): DomainError =
    when (this) {
        is IOException -> DomainError.Network()
        else -> DomainError.Unknown(this)
    }
