package com.example.composenewsapp.news.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rainbow.newsAppModule.news.remote.repository.NewsRepository
import com.example.rainbow.newsAppModule.news.remote.response.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {
    val source = listOf("abc-news","abc-news-au","al-jazeera-english","australian-financial-review", "bbc-news","bloomberg","business-insider","google-news-in","google-news-is","hacker-news")
    val news = newsRepository.getNews(source = source)
        .cachedIn(viewModelScope)


    fun getSearchNews(query: String): Flow<PagingData<Article>> {
        return newsRepository.getNewsSearch(
            source = source, query = query
        )
            .cachedIn(viewModelScope)
    }
}