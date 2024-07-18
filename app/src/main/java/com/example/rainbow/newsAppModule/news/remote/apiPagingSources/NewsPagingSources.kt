package com.example.rainbow.newsAppModule.news.remote.apiPagingSources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rainbow.baseUtils.APISecretKeysFile
import com.example.rainbow.newsAppModule.news.remote.communication.NewsApi
import com.example.rainbow.newsAppModule.news.remote.response.Article

class NewsPagingSources(
    private val newsApi: NewsApi,
    private val sources:String
    ):PagingSource<Int, Article>() {

    private var totalNewCount = 0

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {anchorPosition->
            val anchorPage = state.closestPageToPosition(anchorPosition = anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1

        return try {
            val response = newsApi.getNews(page=page,sources= sources , apiKey = APISecretKeysFile.NEWS_API_KEY)
            totalNewCount += response.articles.size
            val article = response.articles.distinctBy {
                it.title
            }
            LoadResult.Page(
                data = article,
                nextKey = if(totalNewCount==response.totalResults) null else page+1,
                prevKey = null
            )

        }catch (e:Exception){
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )

        }
    }
}