package com.example.rainbow.offGridModule.offGrid.uiComponent

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LOG_TAG
import com.example.composenewsapp.R
import com.example.rainbow.baseUtils.TimeUtility
import com.example.rainbow.offGridModule.offGrid.datamodel.SongData
import com.example.rainbow.offGridModule.offGrid.enums.MusicControlAction
import values.Dimens


@Composable
fun MusicTimeSlider(
    modifier: Modifier = Modifier,
    sliderColors: SliderColors = SliderDefaults.colors(),
    currProgress: Float = 0f,
    maxVal:Float,
    onValueChange: (progress: Float) -> Unit,
    onValueChangeFinished: () -> Unit
) {

    Column(modifier = modifier.fillMaxWidth()) {
        Slider(
            value = currProgress,
            steps = 1,
            valueRange = 0f..maxVal,
            colors = sliderColors,
            onValueChange = { progress ->
                onValueChange(progress)
            },
            onValueChangeFinished = {
                onValueChangeFinished()
            })
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.padding_8),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(modifier = Modifier, text = TimeUtility.getMilliToMinuteSecond(currProgress.toLong()) , style = MaterialTheme.typography.labelMedium, color = Color.White)
            Text(modifier = Modifier, text = TimeUtility.getMilliToMinuteSecond(maxVal.toLong()) , style = MaterialTheme.typography.labelMedium, color = Color.White)

        }
    }
}

@Composable
fun MusicControlButtons(modifier: Modifier = Modifier,playState:Boolean=false, onMusicControlClicked:(action: MusicControlAction)->Unit) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Icon(
            modifier = Modifier
                .padding(Dimens.padding_5)
                .clickable {
                    onMusicControlClicked(MusicControlAction.PlayPreviousMusic)
                },
            painter = painterResource(id = R.drawable.ic_play_previous_icon),
            contentDescription = "previous song",
            tint = Color.Unspecified
        )

        Icon(
            modifier = Modifier
                .padding(Dimens.padding_5)
                .size(Dimens.padding_65)
                .clickable {
                    onMusicControlClicked(MusicControlAction.PlayPauseMusic)
                },
            painter = painterResource(id = if(playState) R.drawable.ic_pause_icon else R.drawable.ic_play_icon),
            contentDescription = "play pause song",
            tint = Color.Unspecified
        )

        Icon(
            modifier = Modifier
                .padding(Dimens.padding_5)
                .clickable {
                    onMusicControlClicked(MusicControlAction.PlayNextMusic)
                },
            painter = painterResource(id = R.drawable.ic_play_next_icon),
            contentDescription = "previous song",
            tint = Color.Unspecified
        )
    }
}


@Preview
@Composable
private fun MusicTimeSliderPrev() {
    MusicTimeSlider(maxVal = 500f, onValueChange = {}, onValueChangeFinished = {})
}

@Preview
@Composable
private fun MusicControlButtonsPrev() {
    MusicControlButtons(onMusicControlClicked = {})
}