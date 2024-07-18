package com.example.rainbow.newsAppModule.data.local.dao

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.rainbow.newsAppModule.news.remote.response.Source

@ProvidedTypeConverter
class NewsArticleTypeConverter {
    @TypeConverter
    fun sourceToString(source: Source):String{
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun stringToSource(sourceStr:String):Source{
        return sourceStr.split(',').let {
            Source(id = it[0], name = it[1])
        }
    }
}