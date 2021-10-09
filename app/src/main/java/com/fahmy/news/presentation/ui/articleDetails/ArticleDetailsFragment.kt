package com.fahmy.news.presentation.ui.articleDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import coil.compose.rememberImagePainter
import com.fahmy.news.domian.model.Article
import com.fahmy.news.presentation.BaseApplication
import com.fahmy.news.presentation.theme.NewsTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

const val ARTICLE = "ARTICLE"

@AndroidEntryPoint
class ArticleDetailsFragment : Fragment() {


    @Inject
    lateinit var baseApplication: BaseApplication

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                arguments?.getSerializable(ARTICLE)?.let { ARTICLE ->
                    val article = ARTICLE as Article
                    NewsTheme(
                        darkTheme = baseApplication.isDark()
                    ) {
                        Scaffold(
                            topBar = {
                                androidx.compose.material.Surface(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    color = MaterialTheme.colors.surface,
                                    elevation = 8.dp
                                ) {
                                    article.title?.let {
                                        Text(
                                            text = it,
                                            modifier = Modifier.padding(12.dp),
                                            style = TextStyle(
                                                color = MaterialTheme.colors.onSurface,
                                                fontSize = 30.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        )
                                    }


                                }

                            }

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
                                        style = TextStyle(color = MaterialTheme.colors.onSurface),
                                    )

                                }


                                article.content?.let {
                                    Text(
                                        text = it,
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .wrapContentSize(),
                                        style = TextStyle(color = MaterialTheme.colors.onSurface),
                                    )

                                }

                                article.source?.let {
                                    Text(
                                        text = it,
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .wrapContentSize(),
                                        style = TextStyle(color = MaterialTheme.colors.onSurface),
                                    )

                                }

                                article.publishedAt?.let {
                                    Text(
                                        text = it,
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .wrapContentSize(),
                                        style = TextStyle(color = MaterialTheme.colors.onSurface),
                                    )

                                }

                            }
                        }

                    }

                }
            }
        }
    }
}

