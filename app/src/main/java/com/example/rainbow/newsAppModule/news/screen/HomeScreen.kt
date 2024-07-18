package com.example.composenewsapp.news.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.example.composenewsapp.R
import com.example.composenewsapp.news.uiComponent.SearchBarView
import com.example.rainbow.newsAppModule.news.remote.response.Article
import values.Dimens


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenpage(article: LazyPagingItems<Article>, onSearchClicked:(searchText:String)->Unit, navigateToDetail:(articleItem:Article)->Unit) {

    val searchText = remember {
        mutableStateOf("")
    }

    val titles = remember {
        derivedStateOf {
            if (article.itemCount > 10) {
                article.itemSnapshotList.items.slice(IntRange(0, 9))
                    .joinToString(separator = "\uD83D\uDD34") {
                        it.title
                    }
            } else {
                ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = Dimens.padding_5)
            .statusBarsPadding()
    ) {

        Image(
            modifier = Modifier
                .width(Dimens.padding_170)
                .padding(horizontal = Dimens.padding_16, vertical = Dimens.padding_12),
            contentScale = ContentScale.FillWidth,
            painter = painterResource(id = R.drawable.news_logo_image),
            contentDescription = ""
        )

        SearchBarView(
            modifier = Modifier,
            text = searchText.value,
            readonly = false,
            onValueChange = {
                      searchText.value = it
            },
            onClick = {},
            onSearch = {
                onSearchClicked(searchText.value)
            })
        Spacer(modifier = Modifier.height(Dimens.padding_8))

        Text(
            text = titles.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.padding_6)
                .basicMarquee(iterations = Int.MAX_VALUE),
            color = colorResource(id = R.color.placeholder),
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(Dimens.padding_8))

        ArticlesList(Modifier, article, onclick = {
            navigateToDetail(it)
        })
    }


}