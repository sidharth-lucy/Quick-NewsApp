package com.example.rainbow.newsAppModule.news.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rainbow.newsAppModule.data.local.dao.NewsArticleDao
import com.example.rainbow.newsAppModule.news.remote.response.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksScreenViewModel @Inject constructor(
    private val newsArticleDao: NewsArticleDao
) : ViewModel() {


////    private var _allBookmarkArticle = mutableStateOf(listOf<Article>())
////    val allBookmarkArticle:State<List<Article>> = _allBookmarkArticle
//
////    private val _allBookmarkArticle = mutableStateOf<List<Article>>(emptyList())
////    val allBookmarkArticle: State<List<Article>> = _allBookmarkArticle
//
//    var allBookmarkArticle = mutableStateOf<List<Article>>(emptyList())
//
//
//
//
//    fun getAllBookMarkedArticle() {
//        viewModelScope.launch(Dispatchers.IO) {
//            newsArticleDao.getAllBookMarkedArticle().collect {
//                allBookmarkArticle = mutableStateOf(it)
//                Log.d("No of item", allBookmarkArticle.toString())
//                Log.d("No of item", allBookmarkArticle.value.count().toString())
//            }
//        }
//    }
//
//    fun deleteArticle(article: Article){
//        viewModelScope.launch(Dispatchers.IO) {
//            if(article.url.isNotEmpty()){
//                newsArticleDao.delete(article)
//                getAllBookMarkedArticle()
//            }
//        }
//    }



    private val _allBookmarkArticle = mutableStateOf<List<Article>>(emptyList())
    val allBookmarkArticle: State<List<Article>> = _allBookmarkArticle

    fun getAllBookMarkedArticle() {
        viewModelScope.launch(Dispatchers.IO) {
            newsArticleDao.getAllBookMarkedArticle().collectLatest { articles ->
                _allBookmarkArticle.value = articles
                Log.d("No of item", allBookmarkArticle.toString())
                Log.d("No of item", allBookmarkArticle.value.count().toString())
            }
        }
    }

    fun deleteArticle(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            if (article.url.isNotEmpty()) {
                newsArticleDao.delete(article)
                // No need to call getAllBookMarkedArticle() here
            }
        }
    }

}