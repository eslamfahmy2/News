package com.fahmy.news.data.paging

import androidx.compose.runtime.mutableStateOf
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fahmy.news.data.dto.ArticleDto
import com.fahmy.news.data.network.ApiInterface
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock

@OptIn(ExperimentalCoroutinesApi::class)
class DataLayerUnitTest {

    /*
    private val itemList = listOf("")

    private lateinit var source: PagedArticleSource

    private val service: ApiInterface = mock()

    @Before
    fun `set up`() {
        source = PagedArticleSource(service, "tesla")
    }
    */

    private val query = "tesla"
    private val totalPage = 2

    private val count by lazy {
        mutableStateOf(0)
    }

    private val service: ApiInterface = mock()

    private val mappingCountCallHandler: HashMap<Int, Int> = HashMap<Int, Int>().apply {
        for (i in 0..totalPage) {
            this[i] = 0
        }
    }

    private lateinit var pager: Flow<PagingData<ArticleDto>>


    @Before
    fun setup() {

        pager = Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 3, // distance backward to get pages
                enablePlaceholders = false,
                initialLoadSize = 20
            ),
            pagingSourceFactory = { PagedArticleSource(service, query) }
        ).flow
    }


    @Test
    fun should_success_get_data_and_not_retrieve_anymore_page_if_not_reached_treshold() {
        runBlocking {
            val job = executeLaunch(this)
            delay(1000)

            Assert.assertEquals(1, mappingCountCallHandler[1])
            Assert.assertEquals(0, mappingCountCallHandler[2])

            job.cancel()
        }
    }


    private fun executeLaunch(coroutineScope: CoroutineScope) = coroutineScope.launch {
        val res = pager.cachedIn(this)
        res.collect {

        }
    }


}