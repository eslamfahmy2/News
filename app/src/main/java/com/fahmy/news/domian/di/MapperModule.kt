package com.fahmy.news.domian.di

import com.fahmy.news.data.network.ApiInterface
import com.fahmy.news.data.paging.ArticleRepoImpl
import com.fahmy.news.domian.mapper.ArticleDtoMapper
import com.fahmy.news.domian.repository.ArticleRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Singleton
    @Provides
    fun provideArticleMapper() = ArticleDtoMapper()

    @Singleton
    @Provides
    fun provideRepository(
        api: ApiInterface,
    ): ArticleRepo {
        return ArticleRepoImpl(api)
    }

}