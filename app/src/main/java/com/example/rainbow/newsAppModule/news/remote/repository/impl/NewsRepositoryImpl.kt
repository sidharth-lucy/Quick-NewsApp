package com.example.rainbow.newsAppModule.news.remote.repository.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rainbow.newsAppModule.news.remote.apiPagingSources.NewsPagingSources
import com.example.rainbow.newsAppModule.news.remote.apiPagingSources.NewsSearchPagingSources
import com.example.rainbow.newsAppModule.news.remote.communication.NewsApi
import com.example.rainbow.newsAppModule.news.remote.repository.NewsRepository
import com.example.rainbow.newsAppModule.news.remote.response.Article
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(private val newsApi: NewsApi): NewsRepository {


    override fun getNews(source: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(10),
            pagingSourceFactory = {
                NewsPagingSources(
                    newsApi = newsApi,
                    sources = source.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override fun getNewsSearch(source: List<String>, query: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsSearchPagingSources(
                    newApi = newsApi,
                    sources = source.joinToString(","),
                    query= query
                )
            }
        ).flow
    }
}