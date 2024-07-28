package com.example.rainbow.offGridModule.offGrid.uiComponent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.rainbow.offGridModule.offGrid.datamodel.SongProgressData
import com.example.rainbow.offGridModule.offGrid.enums.MusicControlAction
import values.Dimens

@Composable
fun MusicHandlerComponent(
    modifier: Modifier = Modifier,
    playState: SongProgressData,
    onMusicControlClicked: (action: MusicControlAction) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        MusicTimeSlider(modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = Dimens.padding_20,
                end = Dimens.padding_20,
                top = Dimens.padding_70
            ),
            maxVal = playState.maxVal,
            currProgress = playState.progress,
            onValueChange = {},
            onValueChangeFinished = {}
        )

        MusicControlButtons(
            modifier = Modifier.padding(
                vertical = Dimens.padding_70,
                horizontal = Dimens.padding_20
            ), playState = playState.playState,
            onMusicControlClicked = onMusicControlClicked
        )
    }
}