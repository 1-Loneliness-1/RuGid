package com.rugid.feature.main.data.api.model.dto

data class ExcursionDto(
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