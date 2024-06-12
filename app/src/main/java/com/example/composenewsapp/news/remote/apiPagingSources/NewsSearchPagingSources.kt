package com.example.composenewsapp.news.remote.apiPagingSources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.composenewsapp.baseUtils.Constants
import com.example.composenewsapp.news.remote.communication.NewsApi
import com.example.composenewsapp.news.remote.response.Article

class NewsSearchPagingSources(
    private val newApi: NewsApi,
    private val sources: String,
    private val query: String
) : PagingSource<Int, Article>() {
    var newsCount = 0
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        val anchorPosition = state.anchorPosition
        if (anchorPosition != null) {
            val anchorPage = state.closestPageToPosition(anchorPosition)
            if (anchorPage?.prevKey != null) {
                return anchorPage.prevKey?.plus(1)
            } else {
                return anchorPage?.nextKey?.minus(1)
            }
        }
        return 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {

        try {
            val page = params.key ?: 1
            val response = newApi.getNewsSearch(
                searchQuery = query,
                sources = sources,
                page = page,
                apiKey = Constants.API_KEY
            )
            newsCount+= response.articles.size
            val article = response.articles.distinctBy {
                it.title
            }
            return LoadResult.Page(
                data = article,
                prevKey = null,
                nextKey = if(newsCount== response.totalResults) null else page+1
            )

        } catch (e: Exception) {
            return LoadResult.Error(throwable = e)
        }

    }

}