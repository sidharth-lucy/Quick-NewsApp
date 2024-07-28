package com.example.rainbow.offGridModule.offGrid.screen
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.rainbow.base.viewmodel.data
import com.example.rainbow.offGridModule.offGrid.datamodel.SongData
import com.example.rainbow.offGridModule.offGrid.datamodel.SongProgressData
import com.example.rainbow.offGridModule.offGrid.enums.MusicControlAction
import com.example.rainbow.offGridModule.offGrid.enums.TopNavigationButtonAction
import com.example.rainbow.offGridModule.offGrid.uiComponent.BackgroundGradient
import com.example.rainbow.offGridModule.offGrid.uiComponent.HorizontalPagerWithAnimation
import com.example.rainbow.offGridModule.offGrid.uiComponent.MusicControlButtons
import com.example.rainbow.offGridModule.offGrid.uiComponent.MusicHandlerComponent
import com.example.rainbow.offGridModule.offGrid.uiComponent.MusicTimeSlider
import com.example.rainbow.offGridModule.offGrid.uiComponent.TopNavigationIcons
import values.Dimens


@Composable
fun OffGridMusicPlayingScreen(
    modifier: Modifier = Modifier,
    songData: SongData,
    data: List<SongData>,
    playState:SongProgressData,
    topNavigationButtonAction: (TopNavigationButtonAction) -> Unit,
    onMusicControlClicked:(action: MusicControlAction)->Unit
) {
    BackgroundGradient(modifier = modifier) {
        Column(modifier = Modifier.fillMaxWidth()) {
            TopNavigationIcons(modifier = Modifier, topNavigationAction = topNavigationButtonAction)

            HorizontalPagerWithAnimation(data = data, songData = songData)

        }
        MusicHandlerComponent(
            modifier= Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            playState = playState,
            onMusicControlClicked=onMusicControlClicked)


    }
}


@Composable
@Preview(showBackground = true)
fun OffGridMusicPlayingScreenPrev(modifier: Modifier = Modifier) {
    OffGridMusicPlayingScreen(modifier = Modifier, songData = data[0], data = data, playState = SongProgressData(true,10f,1000f), topNavigationButtonAction = {}, onMusicControlClicked = {})
}

