package com.fahmy.news.presentation.ui.articles

sealed class ArticlesIntent {
    object Search : ArticlesIntent()
    data class QueryChange(val query: String) : ArticlesIntent()
}
