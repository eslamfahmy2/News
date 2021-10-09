package com.fahmy.news.domian.di

import com.fahmy.news.domian.mapper.ArticleDtoMapper
import com.fahmy.news.domian.repository.ArticleRepo
import com.fahmy.news.domian.useCase.PagedNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Singleton
    @Provides
    fun providePagedSearchUC(
        repo: ArticleRepo,
        mapperModule: ArticleDtoMapper,
    ) = PagedNewsUseCase(repo, mapperModule)


}