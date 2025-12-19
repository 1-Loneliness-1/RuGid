package com.rugid.feature.main.data.mapper

import com.rugid.core.model.Video
import com.rugid.feature.main.data.api.model.dto.VideoDto

class VideoDtoMapper {

    fun map(dto: VideoDto): Video = Video(
        cover = dto.cover,
        title = dto.title
    )

    fun map(domainModel: Video): VideoDto = VideoDto(
        cover = domainModel.cover,
        title = domainModel.title
    )

}