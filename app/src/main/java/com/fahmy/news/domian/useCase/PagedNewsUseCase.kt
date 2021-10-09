package com.fahmy.news.domian.useCase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import com.fahmy.news.domian.mapper.ArticleDtoMapper
import com.fahmy.news.domian.repository.ArticleRepo
import kotlinx.coroutines.flow.map

class PagedNewsUseCase constructor(
    private val repo: ArticleRepo,
    private val mapper: ArticleDtoMapper,
) {
    operator fun invoke(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { repo.listArticles(query = query) }
        ).flow.map { it->
            it.map {
                mapper.mapToDomainModel(it)
            }
        }

}