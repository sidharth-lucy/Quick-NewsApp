package com.example.rainbow.offGridModule.offGrid.screen

import android.net.Uri
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composenewsapp.R
import com.example.composenewsapp.news.uiComponent.background
import com.example.rainbow.offGridModule.offGrid.datamodel.SongData
import com.example.rainbow.offGridModule.offGrid.uiComponent.BackgroundGradient
import com.example.rainbow.offGridModule.offGrid.uiComponent.HorizontalMusicScroll
import com.example.rainbow.offGridModule.offGrid.uiComponent.HorizontalPagerWithAnimation
import com.example.rainbow.offGridModule.offGrid.uiComponent.MusicControlButtons
import com.example.rainbow.offGridModule.offGrid.uiComponent.MusicTimeSlider
import com.example.rainbow.offGridModule.offGrid.uiComponent.TopNavigationIcons
import com.example.rainbow.ui.theme.Color_2600BE_50
import com.example.rainbow.ui.theme.Color_280047
import com.example.rainbow.ui.theme.Color_760181_50
import values.Dimens


@Composable
fun OffGridMusicPlayingScreen(modifier: Modifier = Modifier, data: List<SongData> = emptyList()) {
    BackgroundGradient(modifier = modifier) {
        Column(modifier = Modifier.fillMaxWidth()) {
            TopNavigationIcons(modifier=Modifier, topNavigationAction = {navActionType->

            })

            HorizontalPagerWithAnimation(data = data)

        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)) {
            MusicTimeSlider(modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = Dimens.padding_20,
                    end = Dimens.padding_20,
                    top = Dimens.padding_70
                ),
                currProgress = 0.2f,
                onValueChange = {},
                onValueChangeFinished = {}
            )

            MusicControlButtons(
                modifier = Modifier.padding(
                    vertical = Dimens.padding_70,
                    horizontal = Dimens.padding_20
                ),
                onMusicControlClicked = {actionType->

                }
            )
        }

    }
}


@Composable
@Preview(showBackground = true)
fun OffGridMusicPlayingScreenPrev(modifier: Modifier = Modifier) {
    OffGridMusicPlayingScreen(modifier = Modifier, data = data)
}


val data = listOf(
    SongData(
        name = "OffGrid",
        extraData = "Electro Beat",
        duration = 180000, // 3 minutes in milliseconds
        uriSong = Uri.parse("https://images.unsplash.com/photo-1525362081669-2b476bb628c3?q=80&w=3456&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),

        ),
    SongData(
        name = "Chill Vibes",
        extraData = "Relaxing Tunes",
        duration = 240000, // 4 minutes in milliseconds
        uriSong = Uri.parse(""),
    ),
    SongData(
        name = "Workout Mix",
        extraData = "High Energy",
        duration = 300000, // 5 minutes in milliseconds
        uriSong = Uri.parse("https://example.com/songs/offgrid.mp3"),
    ),
    SongData(
        name = "Evening Jazz",
        extraData = "Smooth Jazz",
        duration = 360000, // 6 minutes in milliseconds
        uriSong = Uri.parse("https://example.com/songs/offgrid.mp3"),
    )
)