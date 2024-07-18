package com.example.composenewsapp.news.screen

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.composenewsapp.R
import com.example.composenewsapp.news.uiComponent.DetailsScreenToolbar
import com.example.rainbow.newsAppModule.news.remote.response.Article
import com.example.rainbow.newsAppModule.news.remote.response.Source
import com.example.rainbow.ui.theme.ComposeNewsAppTheme
import values.Dimens


@Composable
fun NewsDetailScreen(
    modifier: Modifier,
    context: Context,
    article: Article,
    navigateUp: () -> Unit,
    onBookMarkClick:(article:Article)->Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
    )
    {
        DetailsScreenToolbar(modifier = Modifier,
            onBookmarkClick = {
                onBookMarkClick(article)
            },
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(article.url)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onShareNewsClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, article.url)
                    it.type = "text/plain"
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBackClick = {
                navigateUp()
            })

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.padding_16)
        ) {
            item {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimens.padding_250).padding(vertical = Dimens.padding_7).clip(
                            RoundedCornerShape(bottomStart = Dimens.padding_16, topEnd = Dimens.padding_16)
                        ),
                    contentScale = ContentScale.Crop,
                    model = ImageRequest.Builder(context).data(article.urlToImage).placeholder(
                        R.drawable.onboarding1
                    ).build(),
                    contentDescription = "news image"
                )

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(
                        id = R.color.text_title
                    )
                )
                Text(
                    text = article.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(
                        id = R.color.body
                    )
                )
            }

        }


    }
}
val article = Article(
    source = Source("798", "Example"),
    author = "John Doe",
    title = "Understanding Data Classes in Kotlin",
    description = "Learn how to use data classes in Kotlin with an example.",
    url = "https://example.com/kotlin-data-classes",
    urlToImage = "https://example.com/images/article.jpg",
    content = "Both these examples produce the exact same result and are a valid way of adding space to a component. Surely, this must be a mistake. Why else would Jetpack Compose expose two ways of doing the same thing otherwise \uD83E\uDD14 Well, there are some subtle differences between the two approaches that aren't obvious on the surface. Let's talk about these differences and see how they affect the reusability of your composable function.",
    publishedAt = "2024-04-17"
)



@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PrevDetail() {


    ComposeNewsAppTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            NewsDetailScreen(Modifier ,LocalContext.current , article,{},{})
        }
    }
}