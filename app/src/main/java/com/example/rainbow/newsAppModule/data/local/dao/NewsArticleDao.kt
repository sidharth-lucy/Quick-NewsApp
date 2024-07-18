package com.example.rainbow.newsAppModule.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rainbow.newsAppModule.news.remote.response.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article)

    @Delete
    suspend fun delete(article: Article)

    @Query("SELECT * FROM USER_BOOKMARK_ARTICLE_TABLE")
    fun getAllBookMarkedArticle(): Flow<List<Article>>
}