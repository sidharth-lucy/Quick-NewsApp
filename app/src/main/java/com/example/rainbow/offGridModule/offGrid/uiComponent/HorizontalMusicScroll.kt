package com.example.rainbow.offGridModule.offGrid.uiComponent

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.composenewsapp.R
import com.example.rainbow.base.viewmodel.data
import com.example.rainbow.offGridModule.offGrid.datamodel.SongData
import com.example.rainbow.ui.theme.Color_FFFFFF_50
import values.Dimens

@Composable
fun HorizontalMusicScroll(modifier: Modifier = Modifier,data:List<SongData>) {
    LazyRow(modifier= modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Dimens.padding_20)
    ) {
        items(data, key = {it.name}){songItem->
            MusicItemComponent(modifier= Modifier, itemData = songItem)
        }
    }
}

@Composable
fun MusicItemComponent(modifier: Modifier = Modifier, itemData: SongData) {
    val context = LocalContext.current
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            modifier= Modifier
                .height(270.dp)
                .width(235.dp)
                .clip(shape = RoundedCornerShape(Dimens.padding_20)),
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




@Preview
@Composable
private fun MusicItemComponentPrev() {
    MusicItemComponent(modifier = Modifier, itemData = data[1])
}

@Preview
@Composable
private fun HorizontalMusicScrollPrev() {

    HorizontalMusicScroll(modifier = Modifier, data =data)
}