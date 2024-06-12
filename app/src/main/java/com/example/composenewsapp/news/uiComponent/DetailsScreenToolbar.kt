package com.example.composenewsapp.news.uiComponent

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toolbar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.size.Dimension
import com.example.composenewsapp.R
import values.Dimens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreenToolbar(modifier: Modifier, onBrowsingClick: (uri: String) -> Unit, onShareNewsClick:()->Unit, onBookmarkClick:()->Unit,onBackClick:()->Unit) {

    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = {},
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = colorResource(id = R.color.body),
            navigationIconContentColor = colorResource(id = R.color.body)
        ),
        navigationIcon = {
            IconButton(onClick = {onBackClick()}) {
                Icon(painter = painterResource(id = R.drawable.left_arrow), contentDescription = "")
            }
        },
        actions = {
            IconButton(onClick = {onBrowsingClick("")}) {
                Icon(
                    modifier = Modifier.size(Dimens.padding_32),
                    painter = painterResource(id = R.drawable.ic_network_global),
                    contentDescription = ""
                )
            }
            IconButton(onClick = {onShareNewsClick()}) {
                Icon(
                    modifier = Modifier.size(Dimens.padding_32),
                    imageVector = Icons.Default.Share,
                    contentDescription = ""
                )
            }
            IconButton(onClick = {onBookmarkClick()}) {
                Icon(
                    modifier = Modifier.size(Dimens.padding_32),
                    painter = painterResource(id = R.drawable.ic_bookmarks),
                    contentDescription = ""
                )
            }
        }
    )
}


@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
private fun ToolbarPrev() {
    DetailsScreenToolbar(Modifier,{},{},{},{})
}