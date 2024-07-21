package com.example.rainbow.base.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemColors
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.composenewsapp.R
import values.Dimens

@Composable
fun NavigationDrawerContent(
    modifier: Modifier = Modifier,
    selectedApp: AppModuleType = AppModuleType.NEWS_APP_MODULE,
    newSelectedModule:(module:AppModuleType) ->Unit
) {

    ModalDrawerSheet(modifier = modifier) {

        Row(
            modifier = Modifier.padding(
                start = Dimens.padding_16,
                top = Dimens.padding_30,
                end = Dimens.padding_8
            ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(80.dp) // Adjust the size as needed
                    .clip(shape = CircleShape)
                    .border(width = 1.dp, color = Color.Black, shape = CircleShape),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.default_music_img),
                contentDescription = ""
            )

            Column {
                Text(
                    modifier = Modifier.padding(start = Dimens.padding_16),
                    text = "Hi,",
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    fontSize = TextUnit(18f, type = TextUnitType.Sp)
                )
                Text(
                    modifier = Modifier.padding(start = Dimens.padding_16),
                    text = "Sidharth",
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = TextUnit(22f, type = TextUnitType.Sp)
                )
            }


        }

        Divider(modifier = Modifier.padding(top = Dimens.padding_16))

        NavigationDrawerItem(
            modifier = Modifier.padding(
                horizontal = Dimens.padding_16,
                vertical = Dimens.padding_8
            ),
            label = {
                Text(
                    modifier = Modifier,
                    style = MaterialTheme.typography.titleMedium,
                    text = "Quick News"
                )
            },
            selected = selectedApp == AppModuleType.NEWS_APP_MODULE,
            onClick = {
                newSelectedModule(AppModuleType.NEWS_APP_MODULE)
            },
            icon = {
                Icon(
                    modifier = Modifier,
                    painter = painterResource(id = R.drawable.ic_news_logo),
                    contentDescription = "News App"
                )
            },
            colors = NavigationDrawerItemDefaults.colors(
                selectedIconColor = Color.Red,
                selectedTextColor = Color.Red
            )
        )

        NavigationDrawerItem(
            modifier = Modifier
                .padding(horizontal = Dimens.padding_16, vertical = Dimens.padding_8),
            label = {
                Text(
                    modifier = Modifier,
                    style = MaterialTheme.typography.titleMedium,
                    text = "OffGrid"
                )
            },
            selected = selectedApp == AppModuleType.MUSIC_APP_MODULE,
            onClick = {
                newSelectedModule(AppModuleType.MUSIC_APP_MODULE)
            },
            icon = {
                Icon(
                    modifier = Modifier
                        .padding(start = Dimens.padding_15, end = Dimens.padding_17)
                        .size(Dimens.padding_20),
                    painter = painterResource(id = R.drawable.offgrid_music_icon),
                    contentDescription = "News App"
                )
            },
            colors = NavigationDrawerItemDefaults.colors(
                selectedIconColor = Color.Red,
                selectedTextColor = Color.Red
            )
        )


    }

}


@Preview
@Composable
private fun NavigationDrawerContentPrev() {
    NavigationDrawerContent(modifier = Modifier, newSelectedModule = {})
}