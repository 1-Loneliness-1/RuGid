package com.rugid.feature.main.domain.interactor

import com.rugid.core.domain.result.DataResult
import com.rugid.feature.main.domain.model.MainData
import kotlinx.coroutines.flow.Flow

interface GetMainScreenContentInteractor {

    fun getContentForMainScreen(): Flow<DataResult<MainData>>

}