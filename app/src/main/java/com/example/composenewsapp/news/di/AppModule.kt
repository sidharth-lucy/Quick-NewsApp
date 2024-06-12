package com.example.composenewsapp.news.di

import android.app.Application
import androidx.room.Room
import com.example.composenewsapp.baseUtils.Constants
import com.example.composenewsapp.data.PrefDataStore
import com.example.composenewsapp.data.impl.PrefDataStoreImpl
import com.example.composenewsapp.data.local.dao.NewsArticleDao
import com.example.composenewsapp.data.local.dao.NewsArticleTypeConverter
import com.example.composenewsapp.data.local.dao.NewsDataBase
import com.example.composenewsapp.news.remote.communication.NewsApi
import com.example.composenewsapp.news.remote.repository.NewsRepository
import com.example.composenewsapp.news.remote.repository.impl.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePreDataStore(application: Application): PrefDataStore {
        return PrefDataStoreImpl(application)
    }

    @Provides
    @Singleton
    fun provideNewsApi():NewsApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }


    @Provides
    @Singleton
    fun provideNewsRepository(newsApi:NewsApi): NewsRepository{
        return NewsRepositoryImpl(newsApi)
    }


    @Provides
    @Singleton
    fun provideNewsDataBase(
        application: Application
    ):NewsDataBase{
        return Room.databaseBuilder(
            context = application,
            klass = NewsDataBase::class.java,
            name = Constants.NEWS_DATABASE_NAME).
                addTypeConverter(NewsArticleTypeConverter())
            .fallbackToDestructiveMigrationFrom().build()
    }

    @Provides
    @Singleton
    fun provideNewDao(newsDataBase: NewsDataBase):NewsArticleDao{
        return newsDataBase.newsDao
    }
}