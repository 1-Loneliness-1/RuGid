package com.rugid.feature.main.data.mapper

import com.rugid.core.model.Place
import com.rugid.feature.main.data.api.model.dto.PlaceDto

class PlaceDtoMapper {

    fun map(dto: PlaceDto): Place = Place(
        cover = dto.cover,
        title = dto.title,
        category = dto.category,
        rating = dto.rating
    )

    fun map(domainModel: Place): PlaceDto = PlaceDto(
        cover = domainModel.cover,
        title = domainModel.title,
        category = domainModel.category,
        rating = domainModel.rating
    )

}