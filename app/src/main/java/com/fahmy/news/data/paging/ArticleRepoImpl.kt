package com.fahmy.news.data.paging

import com.fahmy.news.data.network.ApiInterface
import com.fahmy.news.domian.repository.ArticleRepo
import javax.inject.Inject

class ArticleRepoImpl @Inject constructor(
    private val api: ApiInterface,
) : ArticleRepo {

    override fun listArticles(query: String): PagedArticleSource {
        return PagedArticleSource(query = query, source = api)
    }


}