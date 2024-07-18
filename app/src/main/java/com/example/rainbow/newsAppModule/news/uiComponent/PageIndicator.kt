package com.example.composenewsapp.news.uiComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import values.Dimens.padding_3


@Preview(showBackground = true)
@Composable
fun showIndicator() {
    PageIndicator(
        Modifier,
        50.dp,
        3,
        1,
        MaterialTheme.colorScheme.primary,
        Color.Gray
    )
}



@Composable
fun PageIndicator(
    modifier: Modifier,
    size: Dp,
    pageCount: Int,
    selectedPage:Int,
    selectedColor: Color,
    unSelectedColor: Color
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(padding_3)) {
        repeat(pageCount) {
            Box(
                modifier = Modifier
                    .size(size = size)
                    .clip(CircleShape)
                    .background(if(selectedPage==it) selectedColor else unSelectedColor)
            ) {

            }
        }
    }

}