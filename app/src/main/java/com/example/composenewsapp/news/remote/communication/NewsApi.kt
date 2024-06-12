package com.example.composenewsapp.news.remote.communication

import com.example.composenewsapp.news.datamodel.Page
import com.example.composenewsapp.news.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String
    ):NewsResponse

    @GET("everything")
    suspend fun getNewsSearch(
        @Query("q") searchQuery:String,
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String
    ):NewsResponse
}