package com.rugid.feature.main.domain.repository

import com.rugid.core.model.Article
import com.rugid.core.model.Excursion
import com.rugid.core.model.Place
import com.rugid.core.model.Video
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun getVideos(): Flow<List<Video>>

    fun getArticles(): Flow<List<Article>>

    fun getPlaces(): Flow<List<Place>>

    fun getExcursions(): Flow<List<Excursion>>

}