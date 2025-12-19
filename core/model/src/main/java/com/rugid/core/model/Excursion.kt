package com.rugid.core.model

data class Excursion(
    val cover: String,
    val title: String,
    val category: String,
    val type: String,
    val duration: Int,
    val numberOfPeople: Int,
    val price: Long,
    val sale: Int = 0,
    val rating: Float
)