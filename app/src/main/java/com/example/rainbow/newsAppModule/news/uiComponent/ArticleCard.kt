package com.example.composenewsapp.news.uiComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.composenewsapp.R
import com.example.rainbow.newsAppModule.news.remote.response.Article
import values.Dimens
import kotlin.math.absoluteValue


@Composable
fun ArticleCard(
    modifier: Modifier,
    article: Article,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    val interactionSource = remember{
            MutableInteractionSource()
    }
    Row(modifier = modifier
        .fillMaxWidth()
        .hoverable(interactionSource=interactionSource)
        .padding(horizontal = Dimens.padding_16)
        .clickable { onClick() }) {
        AsyncImage(
            modifier = Modifier
                .size(Dimens.padding_90)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(context = context)
                .data(article.urlToImage).build(),
            contentDescription = "News image"
        )

        Column(
            modifier = Modifier
                .height(Dimens.padding_90)
                .padding(start = Dimens.padding_8),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = article.title,
                modifier = Modifier,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(
                    id = R.color.text_title
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(
                        id = R.color.body
                    )
                )

                Icon(
                    painter = painterResource(id = R.drawable.clock),
                    contentDescription = "clock",
                    modifier = Modifier
                        .size(Dimens.padding_10)
                        .padding(horizontal = Dimens.padding_4),
                    tint = if(isSystemInDarkTheme()) Color.White else Color.Gray
                )
                Text(
                    text = article.publishedAt,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(
                        id = R.color.body
                    )
                )

            }
        }
    }
}



@Composable
fun Modifier.background(hovered:Boolean=false):Modifier {
    if (hovered) {
        return border(
            width = Dimens.padding_1,
            color = Color.White,
            shape = MaterialTheme.shapes.medium
        )
    } else {
        return this
    }
}