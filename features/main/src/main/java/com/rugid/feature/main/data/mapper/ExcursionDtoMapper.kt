package com.rugid.feature.main.data.mapper

import com.rugid.core.model.Excursion
import com.rugid.feature.main.data.api.model.dto.ExcursionDto

class ExcursionDtoMapper {

    fun map(dto: ExcursionDto): Excursion = Excursion(
        cover = dto.cover,
        title = dto.title,
        category = dto.category,
        type = dto.type,
        duration = dto.duration,
        numberOfPeople = dto.numberOfPeople,
        price = dto.price,
        sale = dto.sale,
        rating = dto.rating
    )

    fun map(domainModel: Excursion): ExcursionDto = ExcursionDto(
        cover = domainModel.cover,
        title = domainModel.title,
        category = domainModel.category,
        type = domainModel.type,
        duration = domainModel.duration,
        numberOfPeople = domainModel.numberOfPeople,
        price = domainModel.price,
        sale = domainModel.sale,
        rating = domainModel.rating
    )

}