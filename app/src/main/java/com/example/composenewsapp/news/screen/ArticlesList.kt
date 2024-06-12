package com.example.composenewsapp.news.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.composenewsapp.news.remote.response.Article
import com.example.composenewsapp.news.uiComponent.ArticleCard
import com.example.composenewsapp.news.uiComponent.NewCardShimmerView
import values.Dimens


@Composable
fun ArticlesList(
    modifier:Modifier,
    article:LazyPagingItems<Article>,
    onclick:(articleItem:Article)->Unit
) {
    val handlePagingRule = HandlePagingRule(article = article)
    if(handlePagingRule){
        LazyColumn(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(Dimens.padding_5)){
            items(count = article.itemCount){
                article[it]?.let {
                    ArticleCard(Modifier, it){
                        onclick(it)
                    }
                }
            }
        }
    }
}


@Composable
fun HandlePagingRule(
    article:LazyPagingItems<Article>
):Boolean {
    val loadState = article.loadState
    val error = when{
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else ->null
    }

    return when{
        loadState.refresh is LoadState.Loading ->{
            shimmerEffect()
            false
        }
        error!=null->{
            EmptyScreen(error)
            false
        }
        else ->{
            true
        }
    }
}


@Composable
fun shimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(Dimens.padding_5)) {
        repeat(10){
            NewCardShimmerView(Modifier)
        }
    }
}