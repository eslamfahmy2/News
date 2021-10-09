package com.fahmy.news.presentation.ui.articles.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.fahmy.news.domian.model.Article


@ExperimentalMaterialApi
@Composable
fun ArticleCard(
    article: Article,
    onClick: (Article) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = { onClick(article) },
        shape = MaterialTheme.shapes.small,
        elevation = 8.dp

    ) {
        Column {

            Image(
                painter = rememberImagePainter(article.urlToImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            )

            article.title?.let {

                Text(
                    text = it,
                    modifier = Modifier
                        .padding(8.dp)
                        .wrapContentSize(),
                    style = TextStyle(
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Bold
                    ),
                )

            }

            article.author?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .wrapContentSize(),
                    style = TextStyle(
                        color = MaterialTheme.colors.onSurface
                    ),
                )

            }

            article.description?.let {

                Text(
                    text = it,
                    modifier = Modifier
                        .padding(8.dp)
                        .wrapContentSize(),
                    style = TextStyle(
                        color = MaterialTheme.colors.onSurface
                    ),
                )

            }

        }

    }
}