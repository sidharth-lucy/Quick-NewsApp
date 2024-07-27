package com.example.rainbow.offGridModule.offGrid.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.rainbow.offGridModule.offGrid.datamodel.SongData
import com.example.rainbow.offGridModule.offGrid.uiComponent.BackgroundGradient
import com.example.rainbow.offGridModule.offGrid.uiComponent.TopActiveMusicCard

@Composable
fun OffGridMusicListScreen(modifier: Modifier = Modifier, allSong:List<SongData>) {
    val context= LocalContext.current
    BackgroundGradient(modifier = modifier) {
        TopActiveMusicCard(modifier= modifier ,context= context, imageUrl = "" )

        LazyColumn(modifier= Modifier) {
            items(count = allSong.size, key = {allSong[it].name}){indexOfSong->

            }
        }
    }
}




@Preview
@Composable
private fun OffGridMusicListScreenPrev() {
    OffGridMusicListScreen(allSong = emptyList())
}

