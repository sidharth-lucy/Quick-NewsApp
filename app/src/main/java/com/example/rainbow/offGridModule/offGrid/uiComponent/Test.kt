package com.example.rainbow.offGridModule.offGrid.uiComponent


import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.elevatedCardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.composenewsapp.R
import com.example.rainbow.offGridModule.offGrid.datamodel.SongData
import com.example.rainbow.ui.theme.Color_280047
import com.example.rainbow.ui.theme.Color_FFFFFF_50
import values.Dimens
import kotlin.math.absoluteValue

@Preview
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DribbbleInspirationPager1() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        val pagerState = rememberPagerState(pageCount = { 10 })
        HorizontalPager(
            pageSpacing = 0.dp,
            beyondBoundsPageCount = 1,
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            Box(modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center) {
                SongInformationCard1(
                    modifier = Modifier
                        .align(Alignment.Center),
                    pagerState = pagerState,
                    page = page
                )
            }

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SongInformationCard1(
    pagerState: PagerState,
    page: Int,
    modifier: Modifier = Modifier
) {
    val pageOffset = pagerState.currentPageOffsetFraction.absoluteValue
    Box(
        modifier = modifier
            .width((270.dp) * (1 - pageOffset))
            .clip(shape = RoundedCornerShape(20.dp)),

    ) {
        MusicItemComponent1(modifier= Modifier,pageOffset=pageOffset, itemData = cc)
    }
}

val cc = SongData(
    name = "Chill Vibes",
    extraData = "Relaxing Tunes",
    duration = 240000, // 4 minutes in milliseconds
    uriSong = Uri.parse(""),
)


@Composable
fun MusicItemComponent1(modifier: Modifier = Modifier,pageOffset:Float, itemData: SongData) {
    val context = LocalContext.current
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            modifier= Modifier
                .fillMaxWidth()
                .padding(0.5.dp)
                .clip(RoundedCornerShape(20.dp))
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
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = "music image")
        Text(modifier = Modifier.padding(top = Dimens.padding_25),
            color = Color.White,
            style = MaterialTheme.typography.labelMedium, text = itemData.name)
        Text(modifier = Modifier.padding(top = Dimens.padding_5),
            color = Color_FFFFFF_50,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Normal,
            text = itemData.extraData ?: "")
    }
}






@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SongInformationCard2(
    pagerState: PagerState,
    page: Int,
    modifier: Modifier = Modifier
) {
    val xx = ""
    val context = LocalContext.current
    val pageOffset = pagerState.currentPageOffsetFraction.absoluteValue
    Card(
        modifier = modifier
            .size((300.dp) * (1 - pageOffset))
            .shadow(16.dp, ambientColor = Color.LightGray),
        shape = RoundedCornerShape(20.dp),
        colors = elevatedCardColors(containerColor = Color.White)
    ) {

        Column(modifier = Modifier) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.5.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .aspectRatio(1f)
                    .background(Color.Transparent)
                    .graphicsLayer {
                        val scale = lerp(1f, 1.75f, pageOffset)
                        scaleX *= scale
                        scaleY *= scale
                    },
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(context)
                        .data(xx ?: "")
                        .placeholder(R.drawable.default_music_img)
                        .build()
                ),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )


        }
    }
}
