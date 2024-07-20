package com.example.rainbow.offGridModule.offGrid.uiComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.composenewsapp.R
import com.example.rainbow.ui.theme.Color_2600BE_50
import com.example.rainbow.ui.theme.Color_280047
import com.example.rainbow.ui.theme.Color_760181_50
import values.Dimens


@Composable
fun BackgroundGradient(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.()->Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color_280047)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopEnd)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color_2600BE_50,
                            Color.Transparent
                        ), radius = 800f, center = Offset(1000f, 400f)
                    )
                )

        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomStart)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color_760181_50,
                            Color.Transparent
                        ), radius = 800f, center = Offset(100f, 2000f)
                    )
                )
        )

    }
    Box(
        modifier = Modifier.fillMaxSize(),
        content = content
    )
}


@Preview
@Composable
private fun BackgroundGradientPrev() {
    BackgroundGradient(modifier = Modifier){}
}


