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
import com.example.rainbow.offGridModule.offGrid.enums.MusicControlAction
import com.example.rainbow.offGridModule.offGrid.enums.TopNavigationButtonAction
import com.example.rainbow.offGridModule.offGrid.uiComponent.BackgroundGradient
import com.example.rainbow.offGridModule.offGrid.uiComponent.HorizontalPagerWithAnimation
import com.example.rainbow.offGridModule.offGrid.uiComponent.MusicControlButtons
import com.example.rainbow.offGridModule.offGrid.uiComponent.MusicTimeSlider
import com.example.rainbow.offGridModule.offGrid.uiComponent.TopNavigationIcons
import values.Dimens


@Composable
fun OffGridMusicPlayingScreen(
    modifier: Modifier = Modifier,
    data: List<SongData> = emptyList(),
    topNavigationButtonAction: (TopNavigationButtonAction) -> Unit,
    onMusicControlClicked:(action: MusicControlAction)->Unit
) {
    BackgroundGradient(modifier = modifier) {
        Column(modifier = Modifier.fillMaxWidth()) {
            TopNavigationIcons(modifier = Modifier, topNavigationAction = topNavigationButtonAction)

            HorizontalPagerWithAnimation(data = data)

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
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
                onMusicControlClicked = onMusicControlClicked
            )
        }

    }
}


@Composable
@Preview(showBackground = true)
fun OffGridMusicPlayingScreenPrev(modifier: Modifier = Modifier) {
    OffGridMusicPlayingScreen(modifier = Modifier, data = data, topNavigationButtonAction = {}, onMusicControlClicked = {})
}

