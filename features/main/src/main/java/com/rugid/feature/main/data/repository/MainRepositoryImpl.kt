package com.rugid.feature.main.data.repository

import androidx.core.net.toUri
import com.rugid.core.model.Article
import com.rugid.core.model.Excursion
import com.rugid.core.model.Place
import com.rugid.core.model.Video
import com.rugid.feature.main.data.api.model.dto.ArticleDto
import com.rugid.feature.main.data.api.model.dto.ExcursionDto
import com.rugid.feature.main.data.api.model.dto.PlaceDto
import com.rugid.feature.main.data.api.model.dto.VideoDto
import com.rugid.feature.main.data.mapper.ArticleDtoMapper
import com.rugid.feature.main.data.mapper.ExcursionDtoMapper
import com.rugid.feature.main.data.mapper.PlaceDtoMapper
import com.rugid.feature.main.data.mapper.VideoDtoMapper
import com.rugid.feature.main.domain.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainRepositoryImpl(
    private val videoDtoMapper: VideoDtoMapper,
    private val articleDtoMapper: ArticleDtoMapper,
    private val placeDtoMapper: PlaceDtoMapper,
    private val excursionDtoMapper: ExcursionDtoMapper,
) : MainRepository {

    private val tempDataWithVideos = listOf(
        VideoDto("https://picsum.photos/200/300".toUri(), "Титульник 1 для видео"),
        VideoDto(
            "https://picsum.photos/200/300".toUri(),
            "Длинный титульник для проверки трех точек в конце"
        ),
        VideoDto("https://picsum.photos/200/300".toUri(), "Еще какой-то титульник для количества"),
        VideoDto("https://picsum.photos/200/300".toUri(), "Второй титульник"),
    )
    private val tempDataWithArticles = listOf(
        ArticleDto("https://picsum.photos/300/200".toUri(), "Статья 1", "15.12.2025", 1000),
        ArticleDto(
            "https://picsum.photos/300/200".toUri(),
            "Длинное название для статьи 2",
            "11.06.2021",
            1200
        ),
        ArticleDto("https://picsum.photos/300/200".toUri(), "Еще вариант 2", "08.03.2011", 1000),
        ArticleDto(
            "https://picsum.photos/300/200".toUri(),
            "Какая-то статья чтобы длинная",
            "17.09.2019",
            5530
        ),
        ArticleDto(
            "https://picsum.photos/300/200".toUri(),
            "Последняя статья в списке",
            "01.10.2020",
            10
        ),
    )
    private val tempDataWithPlaces = listOf(
        PlaceDto("https://picsum.photos/300/200".toUri(), "Место 1", "Музей", 4.9f),
        PlaceDto(
            "https://picsum.photos/300/200".toUri(),
            "Длинное название места для проверки",
            "Погулять",
            3.0f
        ),
        PlaceDto(
            "https://picsum.photos/300/200".toUri(),
            "Еще одно какое-то место",
            "Галерея",
            3.9f
        ),
    )
    private val tempDataWithExcursions = listOf(
        ExcursionDto(
            "https://picsum.photos/300/200".toUri(),
            "Экскурсия 1",
            "Автобусная",
            "Обзорная",
            3,
            10,
            10000L,
            20,
            5.0f
        ),
        ExcursionDto(
            "https://picsum.photos/300/200".toUri(),
            "Длинное название экскурсии",
            "Автомобильная",
            "Обзорная",
            1,
            15,
            1500L,
            0,
            3.5f
        ),
        ExcursionDto(
            "https://picsum.photos/300/200".toUri(),
            "Еще одна какая-то экскурсия",
            "Пешая",
            "Обзорная",
            8,
            222,
            32999L,
            40,
            4.1f
        ),
        ExcursionDto(
            "https://picsum.photos/300/200".toUri(),
            "Последняя экскурсия",
            "Автобусная",
            "Обзорная",
            10,
            5,
            9999L,
            0,
            1.2f
        ),
    )

    override fun getVideos(): Flow<List<Video>> {
        return flow {
            // val response: GetVideosResponse = networkClient.doRequest()
            // if (response.code != 200) {
            //      emit(mapError(response.code))
            //      return@flow
            // }

            // val videos = response.data.map(videoMapper::map)
            // emit(DataResult.Success(videos))

            emit(tempDataWithVideos.map(videoDtoMapper::map))
        }.flowOn(Dispatchers.IO)
    }

    override fun getArticles(): Flow<List<Article>> {
        return flow {
            // val response: GetArticlesResponse = networkClient.doRequest()
            // if (response.code != 200) {
            //      emit(mapError(response.code))
            //      return@flow
            // }

            // val videos = response.data.map(videoMapper::map)
            // emit(DataResult.Success(videos))

            emit(tempDataWithArticles.map(articleDtoMapper::map))
        }.flowOn(Dispatchers.IO)
    }

    override fun getPlaces(): Flow<List<Place>> {
        return flow {
            // val response: GetPlacesResponse = networkClient.doRequest()
            // if (response.code != 200) {
            //      emit(mapError(response.code))
            //      return@flow
            // }

            // val videos = response.data.map(videoMapper::map)
            // emit(DataResult.Success(videos))

            emit(tempDataWithPlaces.map(placeDtoMapper::map))
        }.flowOn(Dispatchers.IO)
    }

    override fun getExcursions(): Flow<List<Excursion>> {
        return flow {
            // val response: GetExcursionsResponse = networkClient.doRequest()
            // if (response.code != 200) {
            //      emit(mapError(response.code))
            //      return@flow
            // }

            // val videos = response.data.map(videoMapper::map)
            // emit(DataResult.Success(videos))

            emit(tempDataWithExcursions.map(excursionDtoMapper::map))
        }.flowOn(Dispatchers.IO)
    }

}