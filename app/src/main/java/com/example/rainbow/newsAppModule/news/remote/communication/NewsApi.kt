package com.example.rainbow.newsAppModule.news.remote.communication

import com.example.rainbow.newsAppModule.news.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String
    ): NewsResponse

    @GET("everything")
    suspend fun getNewsSearch(
        @Query("q") searchQuery:String,
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String
    ):NewsResponse
}