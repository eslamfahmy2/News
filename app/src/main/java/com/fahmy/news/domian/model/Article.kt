package com.fahmy.news.domian.model

import java.io.Serializable

data class Article(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val title: String?,
    val url: String?,
    val urlToImage: String?,
    val source: String?,
) : Serializable