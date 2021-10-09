package com.fahmy.news.presentation.ui.articles

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.fahmy.news.domian.useCase.PagedNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "ListViewModel"

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val useCase: PagedNewsUseCase,
) : ViewModel() {

    val userIntent = Channel<ArticlesIntent>(Channel.UNLIMITED)

    private val _query: MutableState<String> = mutableStateOf("tesla")
    val query get() = _query

    private val _state: MutableState<ArticlesState> = mutableStateOf(ArticlesState.Loading)
    val state get() = _state

    init {
        handleIntent()
        viewModelScope.launch {
            userIntent.send(ArticlesIntent.Search)
        }
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect { intent ->
                when (intent) {
                    is ArticlesIntent.Search -> {
                        _state.value = ArticlesState.Loading
                        _state.value = ArticlesState.Success(
                            useCase(query = query.value).cachedIn(viewModelScope)
                        )
                    }
                    is ArticlesIntent.QueryChange -> {
                        _query.value = intent.query
                        userIntent.send(ArticlesIntent.Search)
                    }
                }
            }
        }
    }

}