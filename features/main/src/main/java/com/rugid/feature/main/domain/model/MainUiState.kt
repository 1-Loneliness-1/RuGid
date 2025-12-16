package com.rugid.feature.main.domain.model

import com.rugid.core.model.Article
import com.rugid.core.model.Excursion
import com.rugid.core.model.Place
import com.rugid.core.model.Video

data class MainUiState(
    val videos: List<Video> = emptyList(),
    val articles: List<Article> = emptyList(),
    val places: List<Place> = emptyList(),
    val excursions: List<Excursion> = emptyList(),
)