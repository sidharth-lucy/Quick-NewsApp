package com.example.rainbow.offGridModule.offGrid.screen

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
import com.example.rainbow.offGridModule.offGrid.uiComponent.BackgroundGradient
import com.example.rainbow.ui.theme.Color_2600BE_50
import com.example.rainbow.ui.theme.Color_280047
import com.example.rainbow.ui.theme.Color_760181_50
import values.Dimens


@Composable
fun OffGridMusicPlayingScreen(modifier: Modifier = Modifier) {
    BackgroundGradient(modifier=modifier) {
        Column(modifier= Modifier.fillMaxSize()) {
            Row(modifier=Modifier.fillMaxWidth().padding(horizontal = Dimens.padding_24, vertical = Dimens.padding_40), horizontalArrangement = Arrangement.SpaceBetween) {
                Icon(painter = painterResource(id = R.drawable.ic_back_arrow_icon), contentDescription = "back", tint = Color.White)
                Icon(painter = painterResource(id = R.drawable.ic_two_dot_menu_icon), contentDescription = "back", tint = Color.White)
            }
        }


    }
}


@Composable
@Preview(showBackground = true)
fun OffGridMusicPlayingScreenPrev(modifier: Modifier = Modifier) {
    OffGridMusicPlayingScreen(modifier = Modifier)
}