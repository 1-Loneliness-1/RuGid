package com.rugid.core.model

import android.net.Uri

data class Article(
    val cover: Uri,
    val title: String,
    val dateOfPublication: String,
    val numOfViews: Long,
)