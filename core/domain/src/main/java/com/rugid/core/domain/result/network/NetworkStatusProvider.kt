package com.rugid.core.domain.result.network

interface NetworkStatusProvider {
    fun isConnected(): Boolean
}