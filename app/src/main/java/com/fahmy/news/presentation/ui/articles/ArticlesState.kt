package com.fahmy.news.presentation.ui.articles

import androidx.paging.PagingData
import com.fahmy.news.domian.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


sealed class ArticlesState {
    data class Success(val data: Flow<PagingData<Article>> = flowOf()) : ArticlesState()
    data class Error(val message: String? = null) : ArticlesState()
    object Loading : ArticlesState()
}