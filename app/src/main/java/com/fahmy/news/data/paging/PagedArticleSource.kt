package com.fahmy.news.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fahmy.news.data.dto.ArticleDto
import com.fahmy.news.data.network.ApiInterface

private const val TAG = "PagedRecipes"
private const val UNSPLASH_STARTING_PAGE_INDEX = 1
const val SIZE = 20

class PagedArticleSource(
    private val source: ApiInterface,
    private val query: String,
) : PagingSource<Int, ArticleDto>() {

    override val jumpingSupported: Boolean
        get() = true

    override val keyReuseSupported: Boolean
        get() = true

    override fun getRefreshKey(state: PagingState<Int, ArticleDto>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleDto> {
        return try {
            val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX
            val response = source.search(
                q = query,
                Size = SIZE,
                page = position,
            )
            LoadResult.Page(
                data = response.articles,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (response.articles.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


}