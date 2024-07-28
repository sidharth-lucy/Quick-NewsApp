package com.example.rainbow.offGridModule.offGrid.uiComponent

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.composenewsapp.R
import com.example.rainbow.base.viewmodel.cc
import com.example.rainbow.offGridModule.offGrid.datamodel.SongData
import com.example.rainbow.ui.theme.Color_FFFFFF_50
import values.Dimens
import kotlin.math.absoluteValue


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerWithAnimation(modifier: Modifier=Modifier,data: List<SongData>,songData: SongData) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        val pagerState = rememberPagerState(pageCount = { data.size })
        val selectedIndex = data.indexOf(songData)
        LaunchedEffect(selectedIndex) {
            pagerState.animateScrollToPage(selectedIndex)
        }
        HorizontalPager(
            pageSpacing = 0.dp,
            beyondBoundsPageCount = 1,
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            Box(modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center) {
                SongInformationCard(
                    modifier = Modifier
                        .align(Alignment.Center),
                    pagerState = pagerState,
                    page = page,
                    currSongData = data[page]
                )
            }

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SongInformationCard(
    pagerState: PagerState,
    page: Int,
    currSongData: SongData,
    modifier: Modifier = Modifier
) {
    val pageOffset = pagerState.currentPageOffsetFraction.absoluteValue
    Box(
        modifier = modifier
//            .width((290.dp) * (1 - pageOffset))
            .fillMaxWidth((0.8f) * (1 - pageOffset))
            .clip(shape = RoundedCornerShape(Dimens.padding_20)),

        ) {
        MusicItemComponent(modifier= Modifier,pageOffset=pageOffset, itemData = currSongData)
    }
}

@Composable
fun MusicItemComponent(modifier: Modifier = Modifier,pageOffset:Float, itemData: SongData) {
    val context = LocalContext.current
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            modifier= Modifier
                .fillMaxWidth()
                .padding(0.5.dp)
                .clip(RoundedCornerShape(Dimens.padding_20))
                .aspectRatio(1f)
                .background(Color.Transparent)
                .graphicsLayer {
                    val scale = lerp(1f, 1.75f, pageOffset)
                    scaleX *= scale
                    scaleY *= scale
                },
            model = ImageRequest.Builder(context)
                .data(itemData.uriSongImage ?: "")
                .placeholder(R.drawable.default_music_img)
                .error(R.drawable.default_music_img)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = "music image")
        Text(modifier = Modifier.padding(top = Dimens.padding_25),
            color = Color.White,
            style = MaterialTheme.typography.labelMedium,
            fontSize = 20.sp,text = itemData.name)
        Text(modifier = Modifier.padding(top = Dimens.padding_5),
            color = Color_FFFFFF_50,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Normal,
            text = itemData.extraData ?: "")
    }
}