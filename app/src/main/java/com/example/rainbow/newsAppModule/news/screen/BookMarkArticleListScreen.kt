package com.example.composenewsapp.news.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composenewsapp.R
import com.example.composenewsapp.news.uiComponent.ArticleCard
import com.example.composenewsapp.news.uiComponent.SwipeToDeleteContainer
import com.example.rainbow.newsAppModule.news.remote.response.Article
import values.Dimens

@Composable
fun BookMarkArticleListScreen(
    modifier: Modifier,
    article: List<Article>,
    onArticleClick: (articleItem: Article) -> Unit,
    onDeleteArticle: (articleItem: Article) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(vertical = Dimens.padding_6)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.padding_10),
            text = "Bookmarks",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.text_title)
        )
        Spacer(modifier = Modifier.height(Dimens.padding_8))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Dimens.padding_8),
            verticalArrangement = Arrangement.spacedBy(Dimens.padding_5)
        ) {
            items(
                article,
                key = {
                    it.url
                }) { articleItem ->
                SwipeToDeleteContainer(
                    item = articleItem,
                    onDelete = {
                        onDeleteArticle(articleItem)
                    },
                    content = {
                        ArticleCard(Modifier, articleItem) {
                            onArticleClick(articleItem)
                        }
                    })
            }
        }
    }
}


@Composable
fun BookMarkArticleListScreen1(
    modifier: Modifier,
    article: List<Article>,
    onArticleClick: (articleItem: Article) -> Unit,
    onDeleteArticle:(articleItem: Article) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(vertical = Dimens.padding_6)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.padding_10),
            text = "Bookmarks",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.text_title)
        )
        Spacer(modifier = Modifier.height(Dimens.padding_8))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Dimens.padding_8),
            verticalArrangement = Arrangement.spacedBy(Dimens.padding_5)
        ) {
            items(article, key={it.url}) {articleItem->

                val dismissState = rememberSwipeToDismissBoxState(
                    positionalThreshold = {totalDistance->
                        0.4f*totalDistance
                    },
                    confirmValueChange = { dismissValue ->
                        if (dismissValue == SwipeToDismissBoxValue.EndToStart) {
                            onDeleteArticle(articleItem)
                        }
                        return@rememberSwipeToDismissBoxState true
                    }
                )

                SwipeToDismissBox(
                    enableDismissFromStartToEnd = false,
                    state = dismissState,
                    backgroundContent = {
                        val color = when (dismissState.targetValue) {
                            SwipeToDismissBoxValue.EndToStart -> Color.Red.copy(alpha = 0.5f)
                            SwipeToDismissBoxValue.StartToEnd -> Color.Transparent
                            else -> Color.Transparent
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color)
                                .padding(horizontal = 16.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = Color.White
                            )
                        }
                    },
                    content = {
                        ArticleCard(
                            modifier = Modifier,
                            article = articleItem,
                            onClick = { onArticleClick(articleItem) }
                        )
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun PrevBookmark() {
    BookMarkArticleListScreen(Modifier, listOf(), {},{})
}