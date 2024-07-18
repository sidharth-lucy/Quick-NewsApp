package com.example.composenewsapp.news.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
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
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.composenewsapp.R
import com.example.composenewsapp.news.uiComponent.ArticleCard
import com.example.rainbow.newsAppModule.news.remote.response.Article
import values.Dimens

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun BookMarkArticleListScreen(
//    modifier: Modifier,
//    article: List<Article>,
//    onArticleClick: (articleItem: Article) -> Unit,
//    onDeleteArticle:(articleItem: Article) -> Unit
//) {
//    Column(
//        modifier = modifier
//            .fillMaxSize()
//            .statusBarsPadding()
//            .padding(vertical = Dimens.padding_6)
//    ) {
//        Text(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = Dimens.padding_10),
//            text = "Bookmarks",
//            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
//            color = colorResource(id = R.color.text_title)
//        )
//        Spacer(modifier = Modifier.height(Dimens.padding_8))
//
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = Dimens.padding_8),
//            verticalArrangement = Arrangement.spacedBy(Dimens.padding_5)
//        ) {
//            items(
//                article,
//                key = {
//                    it.url
//                }) { articleItem ->
//                        SwipeToDeleteContainer(
//                            item = articleItem,
//                            onDelete = {
//                                onDeleteArticle(articleItem)
//                            },
//                            content = {
//                                ArticleCard(Modifier, articleItem) {
//                                    onArticleClick(articleItem)
//                                }
//                        })
//            }
//        }
//    }
//}





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookMarkArticleListScreen(
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

                val dismissState = rememberDismissState(
                    confirmValueChange = {
                        if (it == DismissValue.DismissedToStart) {
                            onDeleteArticle(articleItem)
                        }
                        return@rememberDismissState true
                    }
                )
                SwipeToDismiss(state = dismissState,
                    background = {
                        val color = when (dismissState.dismissDirection) {
                            DismissDirection.EndToStart -> Color.Red.copy(alpha = 0.5f)
                            DismissDirection.StartToEnd -> Color.Transparent
                            null -> Color.Transparent
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(Dimens.padding_90)
                                .background(color = color)
                        ) {
//                            Icon(
//                                imageVector = Icons.Default.Delete,
//                                contentDescription = "",
//                                tint = Color.White,
//                                modifier = Modifier.align(Alignment.CenterEnd).padding(end = Dimens.padding_16)
//                            )
                        }


                    }, dismissContent = {
                        ArticleCard(Modifier, articleItem,
                            onClick = {
                                onArticleClick(articleItem)
                            })
                    },
                    directions = setOf(DismissDirection.EndToStart)
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