package com.fahmy.news.presentation.ui.articles


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.fahmy.news.presentation.BaseApplication
import com.fahmy.news.presentation.theme.NewsTheme
import com.fahmy.news.presentation.ui.articles.component.AppBar
import com.fahmy.news.presentation.ui.articles.component.DataScreen
import com.fahmy.news.presentation.ui.articles.component.LoadingScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class ArticlesFragment : Fragment() {

    private val viewModel: ArticlesViewModel by viewModels()

    @Inject
    lateinit var baseApplication: BaseApplication

    @ExperimentalMaterialApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {


                NewsTheme(
                    darkTheme = baseApplication.isDark()
                ) {
                    Scaffold(
                        topBar = {
                            AppBar(
                                title = "News",
                                query = viewModel.query.value,
                                onValueChanged = { txt ->
                                    lifecycleScope.launch {
                                        viewModel.userIntent.send(ArticlesIntent.QueryChange(txt))
                                    }
                                },
                                onSearch = {
                                    lifecycleScope.launch {
                                        viewModel.userIntent.send(ArticlesIntent.Search)
                                    }
                                },
                                onToggleTheme = {
                                    baseApplication.toggleTheme()
                                }
                            )
                        }
                    ) {

                        when (val result = viewModel.state.value) {

                            is ArticlesState.Error -> {
                                Text(text = result.message ?: "")
                            }
                            ArticlesState.Loading -> {
                                LoadingScreen()
                            }
                            is ArticlesState.Success -> {
                                val data = result.data.collectAsLazyPagingItems()
                                DataScreen(data = data, navController = findNavController())
                            }
                        }
                    }
                }
            }
        }
    }
}



