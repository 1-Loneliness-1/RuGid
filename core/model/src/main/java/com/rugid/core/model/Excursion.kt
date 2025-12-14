package com.rugid.core.model

import android.net.Uri

data class Excursion(
    val cover: Uri,
    val title: String,
    val category: String,
    val type: String,
    val duration: Int,
    val numberOfPeople: Int,
    val price: Long,
    val sale: Int = 0,
    val rating: Float
)