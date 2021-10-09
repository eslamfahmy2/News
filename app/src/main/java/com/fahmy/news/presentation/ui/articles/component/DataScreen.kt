package com.fahmy.news.presentation.ui.articles.component

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.fahmy.news.R
import com.fahmy.news.domian.model.Article
import com.fahmy.news.presentation.ui.articleDetails.ARTICLE


@ExperimentalMaterialApi
@Composable
fun DataScreen(
    data: LazyPagingItems<Article>,
    navController: NavController,
) {

    if (data.loadState.refresh == LoadState.Loading) {
        LoadingScreen()
    } else {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(data) {
                it?.let {
                    ArticleCard(
                        article = it
                    ) {
                        val bundle = Bundle()
                        bundle.putSerializable(ARTICLE, it)
                        navController.navigate(
                            R.id.action_articlesFragment_to_articleDetailsFragment2,
                            bundle
                        )

                    }
                }

            }
            if (data.loadState.prepend == LoadState.Loading) {
                item {
                    Text(text = "Loading")
                }
            }
        }

    }
}



