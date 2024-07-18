package com.example.composenewsapp.news.uiComponent

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.composenewsapp.R
import com.example.rainbow.ui.theme.ComposeNewsAppTheme
import values.Dimens


@Composable
fun Modifier.newsCardShimmerModifier() :Modifier {
    val transition = rememberInfiniteTransition(label = "")
    val alpha = transition.animateFloat(initialValue = 0.2f, targetValue = 0.9f, animationSpec = infiniteRepeatable(
        animation = tween(1000),
        repeatMode = RepeatMode.Reverse
    ), label = ""
    ).value
    return this then background(color = colorResource(id = R.color.shimmer).copy(alpha= alpha))
}


@Composable
fun NewCardShimmerView(modifier:Modifier) {
    Row(modifier = modifier.padding(horizontal = Dimens.padding_16)) {
        Box(
            modifier = Modifier
                .size(Dimens.padding_90)
                .clip(MaterialTheme.shapes.medium)
                .newsCardShimmerModifier(),
        )

        Column(
            modifier = Modifier
                .height(Dimens.padding_90)
                .padding(Dimens.padding_4),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.padding_30)
                    .padding(start = Dimens.padding_8)
                    .newsCardShimmerModifier(),
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(Dimens.padding_20)
                    .padding(start = Dimens.padding_8)
                    .newsCardShimmerModifier(),
            )
        }
    }
}


@Preview(showBackground = true)
@Preview(showBackground = true , uiMode = UI_MODE_NIGHT_YES)
@Composable
fun shimmerPrev() {
    ComposeNewsAppTheme{
        NewCardShimmerView(Modifier)
    }

}