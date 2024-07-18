package com.example.rainbow.newsAppModule.news.remote.repository

import androidx.paging.PagingData
import com.example.rainbow.newsAppModule.news.remote.response.Article
import kotlinx.coroutines.flow.Flow


interface NewsRepository {

    fun getNews(source: List<String>) : Flow<PagingData<Article>>

    fun getNewsSearch(source: List<String>,query:String): Flow<PagingData<Article>>
}