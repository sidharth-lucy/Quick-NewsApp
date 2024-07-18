package com.example.composenewsapp.news.uiComponent

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.composenewsapp.R
import values.Dimens


@Composable
fun NewsBottomNavigationBar(
    modifier: Modifier,
    items: List<BottomNavigationBarItem>,
    selected: Int,
    onItemClicked:(itemId:Int)->Unit
) {
    NavigationBar(
        modifier = modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = Dimens.padding_10
    ) {

        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = (index == selected),
                onClick = { onItemClicked(index)},
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(modifier = Modifier.size(Dimens.padding_20), painter = painterResource(id = item.icon) , contentDescription = "")
                        Text(text = item.name , style = MaterialTheme.typography.labelSmall)
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = colorResource(id = R.color.body),
                    unselectedTextColor = colorResource(id = R.color.body)
                ))
        }
    }
}


data class BottomNavigationBarItem(val name: String, @DrawableRes val icon: Int)

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun BottomNavPre() {
    NewsBottomNavigationBar(
        Modifier,
        listOf(BottomNavigationBarItem("home", R.drawable.ic_home),
            BottomNavigationBarItem("Search", R.drawable.ic_search),
            BottomNavigationBarItem("Bookmarks", R.drawable.ic_bookmarks)),
        0,
        {}
        )
}