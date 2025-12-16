package com.rugid.feature.main.data.api.model.dto

import android.net.Uri

data class PlaceDto(
    val cover: Uri,
    val title: String,
    val category: String,
    val rating: Float,
)