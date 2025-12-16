package com.rugid.feature.main.domain.interactor

import com.rugid.core.domain.result.DataResult
import com.rugid.feature.main.domain.model.MainUiState
import kotlinx.coroutines.flow.Flow

interface GetMainScreenContentInteractor {

    suspend fun getContentForMainScreen(): Flow<DataResult<MainUiState>>

}