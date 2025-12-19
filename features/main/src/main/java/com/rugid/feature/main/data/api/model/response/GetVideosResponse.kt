package com.rugid.feature.main.data.api.model.response

import com.rugid.core.model.api.Response
import com.rugid.feature.main.data.api.model.dto.VideoDto

data class GetVideosResponse(
    val data: List<VideoDto>?,
) : Response()