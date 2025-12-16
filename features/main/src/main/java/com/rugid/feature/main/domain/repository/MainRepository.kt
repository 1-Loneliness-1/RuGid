package com.rugid.feature.main.domain.repository

import com.rugid.core.domain.result.DataResult
import com.rugid.core.model.Article
import com.rugid.core.model.Excursion
import com.rugid.core.model.Place
import com.rugid.core.model.Video
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun getVideos(): Flow<DataResult<List<Video>>>

    fun getArticles(): Flow<DataResult<List<Article>>>

    fun getPlaces(): Flow<DataResult<List<Place>>>

    fun getExcursions(): Flow<DataResult<List<Excursion>>>

}