package com.example.composenewsapp.news.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composenewsapp.data.local.dao.NewsArticleDao
import com.example.composenewsapp.news.remote.response.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailScreenViewModel @Inject constructor(private val newsArticleDao: NewsArticleDao):ViewModel(){


    fun addRemoveArticleToBookMark(article: Article){
        viewModelScope.launch(Dispatchers.IO){
            newsArticleDao.upsert(article)
        }
    }

}