package com.example.composenewsapp.news.remote.response

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.composenewsapp.baseUtils.Constants
import kotlinx.parcelize.Parcelize

data class NewsResponse(
    val status:String,
    val totalResults:Int,
    val articles:List<Article>
)


@Parcelize
@Entity(tableName = Constants.USER_BOOKMARK_ARTICLE_TABLE)
data class Article(
    val source:Source,
    val author:String,
    val title:String,
    val description:String,
    @PrimaryKey val url:String,
    val urlToImage:String,
    val content:String,
    val publishedAt:String
):Parcelable

@Parcelize
data class Source(
    val id:String,
    val name:String
):Parcelable
