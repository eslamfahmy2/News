package com.fahmy.news

import androidx.paging.ExperimentalPagingApi
import androidx.paging.map
import com.fahmy.news.domian.mapper.ArticleDtoMapper
import com.fahmy.news.domian.model.Article
import com.fahmy.news.domian.useCase.PagedNewsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
@ExperimentalPagingApi
class PostsRepositoryTest {

    private val domainPosts = listOf<Article>(
        Article(
            source = "source",
            publishedAt = "publishedAt",
            content = "content",
            author = "author",
            url = "url",
            urlToImage = "urlToImage",
            description = "description",
            title = "title"
        ),
        Article(
            source = "source",
            publishedAt = "publishedAt",
            content = "content",
            author = "author",
            url = "url",
            urlToImage = "urlToImage",
            description = "description",
            title = "title"
        ),
    )

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val coroutineDispatcher = TestCoroutineDispatcher()

    private val postsApi = mock()

    lateinit var mapper: ArticleDtoMapper

    private lateinit var postsRepository: PagedNewsUseCase

    @Before
    fun createRepository() = coroutineDispatcher.runBlockingTest {

        postsRepository = PagedNewsUseCase(postsApi, mapper = mapper)
    }

    @Test
    fun loadPosts_returnsCorrectPosts() = runBlocking {
        launch {

            postsRepository.invoke("teslla").collect { pagingData ->

                val posts = mutableListOf<Article>()
                pagingData.map {
                    posts.add(it)
                    println(it)
                }

                //THEN: retrieved posts should be the remotePosts

                assertThat(posts, IsEqual(domainPosts))

            }


        }
    }
}