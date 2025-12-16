package com.rugid.feature.main.data.api.model.dto

import android.net.Uri

data class ArticleDto(
    val cover: Uri,
    val title: String,
    val dateOfPublication: String,
    val numOfViews: Long,
)