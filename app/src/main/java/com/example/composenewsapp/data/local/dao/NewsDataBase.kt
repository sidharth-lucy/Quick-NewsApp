package com.example.composenewsapp.data.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.composenewsapp.news.remote.response.Article


@Database(entities = [Article::class], version = 1)
@TypeConverters(NewsArticleTypeConverter::class)
abstract class NewsDataBase:RoomDatabase() {
    abstract val newsDao:NewsArticleDao
}