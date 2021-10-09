package com.fahmy.news.domian.mapper

import com.fahmy.news.data.dto.ArticleDto
import com.fahmy.news.domian.model.Article

class ArticleDtoMapper : DomainMapper<ArticleDto, Article> {

    override fun mapToDomainModel(model: ArticleDto): Article {
        return Article(
            title = model.title,
            description = model.description,
            content = model.content,
            author = model.author,
            publishedAt = model.publishedAt,
            url = model.url,
            urlToImage = model.urlToImage,
            source = model.source.name
        )
    }


}