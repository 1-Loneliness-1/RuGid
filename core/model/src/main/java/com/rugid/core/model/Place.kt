package com.rugid.core.model

import android.net.Uri

data class Place(
    val cover: Uri,
    val title: String,
    val category: String,
    val rating: Float,
)
