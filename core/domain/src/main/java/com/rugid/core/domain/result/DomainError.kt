package com.rugid.core.domain.result

sealed class DomainError : Throwable() {
    class Network : DomainError()
    class Unauthorized : DomainError()
    class NotFound : DomainError()
    class Server : DomainError()
    class EmptyData : DomainError()
    data class Unknown(override val cause: Throwable) : DomainError()
}