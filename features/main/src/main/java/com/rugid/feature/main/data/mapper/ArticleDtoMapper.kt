package com.rugid.feature.main.data.mapper

import com.rugid.core.model.Article
import com.rugid.feature.main.data.api.model.dto.ArticleDto

class ArticleDtoMapper {

    fun map(dto: ArticleDto): Article = Article(
        cover = dto.cover,
        title = dto.title,
        dateOfPublication = dto.dateOfPublication,
        numOfViews = dto.numOfViews
    )

    fun map(domainModel: Article): ArticleDto = ArticleDto(
        cover = domainModel.cover,
        title = domainModel.title,
        dateOfPublication = domainModel.dateOfPublication,
        numOfViews = domainModel.numOfViews
    )

}