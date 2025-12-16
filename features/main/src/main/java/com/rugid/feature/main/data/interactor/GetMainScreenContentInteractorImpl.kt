package com.rugid.feature.main.data.interactor

import com.rugid.core.domain.result.DataResult
import com.rugid.core.domain.result.DomainError
import com.rugid.core.model.Article
import com.rugid.core.model.Excursion
import com.rugid.core.model.Place
import com.rugid.core.model.Video
import com.rugid.feature.main.domain.interactor.GetMainScreenContentInteractor
import com.rugid.feature.main.domain.model.MainUiState
import com.rugid.feature.main.domain.repository.MainRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

class GetMainScreenContentInteractorImpl(
    private val mainRepository: MainRepository
) : GetMainScreenContentInteractor {

    override suspend fun getContentForMainScreen(): Flow<DataResult<MainUiState>> = flow {
        try {
            coroutineScope {
                val videos = async { mainRepository.getVideos().firstOrNull() }
                val articles = async { mainRepository.getArticles().firstOrNull() }
                val places = async { mainRepository.getPlaces().firstOrNull() }
                val excursions = async { mainRepository.getExcursions().firstOrNull() }

                val receivedVideos = videos.await()
                val receivedArticles = articles.await()
                val receivedPlaces = places.await()
                val receivedExcursions = excursions.await()

                val errorInResults =
                    listOf(receivedVideos, receivedPlaces, receivedArticles, receivedExcursions)
                        .filterIsInstance<DataResult.Error>()
                        .firstOrNull()
                if (errorInResults != null) {
                    emit(DataResult.Error(errorInResults.error))
                } else {
                    emit(
                        DataResult.Success(
                            MainUiState(
                                videos = (receivedVideos as DataResult.Success<List<Video>>).data,
                                articles = (receivedArticles as DataResult.Success<List<Article>>).data,
                                places = (receivedPlaces as DataResult.Success<List<Place>>).data,
                                excursions = (receivedExcursions as DataResult.Success<List<Excursion>>).data,
                            )
                        )
                    )
                }
            }
        } catch (e: Exception) {
            emit(DataResult.Error(DomainError.Unknown(e)))
        }
    }

}