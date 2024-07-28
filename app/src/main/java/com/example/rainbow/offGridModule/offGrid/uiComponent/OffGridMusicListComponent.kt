package com.example.rainbow.offGridModule.offGrid.uiComponent

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.composenewsapp.R
import com.example.rainbow.base.viewmodel.cc
import com.example.rainbow.offGridModule.offGrid.datamodel.SongData
import com.example.rainbow.offGridModule.offGrid.enums.TopNavigationButtonAction
import values.Dimens
import kotlin.random.Random

@Composable
fun TopActiveMusicCard(
    modifier: Modifier = Modifier,
    context: Context,
    imageUrl: String?,
    topNavigationAction: (TopNavigationButtonAction) -> Unit
) {
    Box {
        AsyncImage(
            modifier = modifier
                .fillMaxWidth()
                .height(Dimens.padding_290)
                .clip(
                    shape = RoundedCornerShape(
                        topStart = Dimens.padding_0,
                        topEnd = Dimens.padding_0,
                        bottomEnd = Dimens.padding_45,
                        bottomStart = Dimens.padding_45
                    )
                ),
            model = ImageRequest.Builder(context)
                .data(imageUrl ?: "")
                .placeholder(R.drawable.default_music_img)
                .error(R.drawable.default_music_img)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = "music image"
        )
        TopNavigationIcons(
            modifier = Modifier, iconList = listOf(
                TopNavigationButtonAction.NavigationOtherModuleAction,
                TopNavigationButtonAction.NavigationMoreButtonAction
            ), topNavigationAction = topNavigationAction
        )
        CardDescViewBottom(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(horizontal = Dimens.padding_24, vertical = Dimens.padding_40)
        )
    }

}


@Composable
fun CardDescViewBottom(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Clear Mind",
            color = colorResource(id = R.color.white),
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = "Instrumental . Relaxed . 1Hours",
            color = colorResource(id = R.color.placeholder),
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp, maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Composable
fun MusicListItem(modifier: Modifier = Modifier,
                  context: Context,
                  songData: SongData,
                  onSongClick:(songData: SongData)->Unit) {
    val defaultImg = listOf(
        R.drawable.ic_music_item_default_1,
        R.drawable.ic_music_item_default_2
    )[Random.nextInt(0, 2)]
    Row(modifier = modifier.fillMaxWidth().clickable {
        onSongClick(songData)
    }) {
        Log.d("trackImg", songData.uriSongImage.toString())
        AsyncImage(
            modifier = Modifier
                .size(Dimens.padding_64)
                .clip(shape = RoundedCornerShape(Dimens.padding_15)),
            model = ImageRequest.Builder(context)
                .data(songData.uriSongImage)
                .error(defaultImg)
                .placeholder(R.drawable.default_music_img).build(),
            contentDescription = "music item image",
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .height(Dimens.padding_64)
                .padding(horizontal = Dimens.padding_20),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = songData.name,
                color = colorResource(
                    id = R.color.white
                ),
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = songData.extraData ?: "",
                color = colorResource(
                    id = R.color.placeholder
                ),
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Thin,
                fontSize = 11.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}

@Preview
@Composable
private fun MusicListItemPrev() {
    MusicListItem(context = LocalContext.current, songData = cc, onSongClick = {})
}

@Preview
@Composable
private fun CardDescViewBottomPrev() {
    CardDescViewBottom()
}

@Preview
@Composable
private fun TopActiveMusicCardPrev() {
    val context = LocalContext.current
    TopActiveMusicCard(context = context, imageUrl = "", topNavigationAction = {})
}
