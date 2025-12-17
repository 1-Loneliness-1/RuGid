package com.rugid.feature.main.data.interactor

import com.rugid.core.domain.result.DataResult
import com.rugid.core.domain.result.mapper.toDomainError
import com.rugid.feature.main.domain.interactor.GetMainScreenContentInteractor
import com.rugid.feature.main.domain.model.MainData
import com.rugid.feature.main.domain.repository.MainRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class GetMainScreenContentInteractorImpl(
    private val mainRepository: MainRepository
) : GetMainScreenContentInteractor {

    override fun getContentForMainScreen(): Flow<DataResult<MainData>> =
        combine(
            mainRepository.getVideos(),
            mainRepository.getArticles(),
            mainRepository.getPlaces(),
            mainRepository.getExcursions(),
        ) { videos, articles, places, excursions ->
            MainData(
                videos = videos,
                articles = articles,
                places = places,
                excursions = excursions
            )
        }
            .map { mainData ->
                DataResult.Success(mainData) as DataResult<MainData>
            }
            .catch { throwable ->
                if (throwable is CancellationException) throw throwable
                emit(DataResult.Error(throwable.toDomainError()))
            }

}