package com.fahmy.news.domian.repository

import com.fahmy.news.data.paging.PagedArticleSource

interface ArticleRepo {

    fun listArticles(query: String): PagedArticleSource
}