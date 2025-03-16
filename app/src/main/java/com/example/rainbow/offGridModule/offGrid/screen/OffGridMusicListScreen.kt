package com.example.rainbow.offGridModule.offGrid.screen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.rainbow.base.viewmodel.RainbowResult
import com.example.rainbow.base.viewmodel.data
import com.example.rainbow.offGridModule.offGrid.datamodel.SongData
import com.example.rainbow.offGridModule.offGrid.enums.TopNavigationButtonAction
import com.example.rainbow.offGridModule.offGrid.uiComponent.BackgroundGradient
import com.example.rainbow.offGridModule.offGrid.uiComponent.MusicListItem
import com.example.rainbow.offGridModule.offGrid.uiComponent.TopActiveMusicCard
import values.Dimens

@Composable
fun OffGridMusicListScreen(
    modifier: Modifier = Modifier,
    context: Context,
    allSong: RainbowResult<List<SongData>, String>,
    topNavigationAction: (TopNavigationButtonAction) -> Unit,
    onSongClick:(songData: SongData,indexOfSong:Int)->Unit
) {
    BackgroundGradient(modifier = modifier) {

        Column(modifier = Modifier.fillMaxSize()) {
            TopActiveMusicCard(modifier = modifier, context = context, imageUrl = "", topNavigationAction = topNavigationAction)
            when (allSong) {
                is RainbowResult.Loading -> {

                }

                is RainbowResult.Failure -> {

                }

                is RainbowResult.Success -> {
                    allSong.data.let { songList ->
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = Dimens.padding_20,
                                    vertical = Dimens.padding_40
                                ),
                            verticalArrangement = Arrangement.spacedBy(Dimens.padding_16),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            items(
                                count = songList.size,
                                key = { songList[it].uriSong }) { indexOfSong ->
                                MusicListItem(
                                    modifier = Modifier,
                                    context = context,
                                    songData = songList[indexOfSong],
                                    onSongClick ={onSongClick(it,indexOfSong)}
                                )
                            }
                        }
                    }
                }

            }

        }


    }
}


@Preview
@Composable
private fun OffGridMusicListScreenPrev() {
//    OffGridMusicListScreen(LocalContext.current,allSong = data)
}

