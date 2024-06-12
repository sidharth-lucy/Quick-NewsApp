package com.example.composenewsapp.news.uiComponent

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.composenewsapp.R
import com.example.composenewsapp.ui.theme.ComposeNewsAppTheme
import values.Dimens


@Composable
fun SearchBarView(
    modifier: Modifier,
    text: String,
    readonly: Boolean,
    onClick: (() -> Unit)? = null,
    onValueChange: (inputText:String) -> Unit,
    onSearch: () -> Unit
) {

    val interactionSources = remember {
        MutableInteractionSource()
    }
    val isClicked = interactionSources.collectIsPressedAsState().value
    LaunchedEffect(key1 = isClicked) {
        if (isClicked) {
            onClick?.invoke()
        }
    }
    val localFocus = LocalFocusManager.current


    Box(
        modifier = modifier
            .padding(horizontal = Dimens.padding_16)
    ) {

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .searchBarBoarder(),
            value = text,
            readOnly = readonly,
            onValueChange = {
                onValueChange(it) },
            leadingIcon = {
                Icon(
                    modifier = Modifier.size(Dimens.padding_20),
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "",
                    tint = colorResource(id = R.color.body)
                )
            },
            placeholder = {
                Text(text = "Search" , style = MaterialTheme.typography.bodyMedium , color = colorResource(
                    id = R.color.placeholder
                ))
            },
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorResource(id = R.color.input_background).copy(alpha = 0.9f),
                unfocusedContainerColor = colorResource(id = R.color.input_background).copy(alpha = 0.6f),
                focusedTextColor = if(isSystemInDarkTheme()) Color.White else Color.Black,
                cursorColor = if(isSystemInDarkTheme()) Color.White else Color.Black,
                disabledContainerColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                onSearch()
                localFocus.clearFocus()
            }),
            textStyle = MaterialTheme.typography.bodySmall,
            interactionSource = interactionSources
        )
    }

}




@Composable
fun Modifier.searchBarBoarder(): Modifier {
    if (!isSystemInDarkTheme()) {
        return border(
            width = Dimens.padding_1,
            color = Color.Black,
            shape = MaterialTheme.shapes.medium
        )
    } else {
        return this
    }
}


@Preview(showBackground = true)
@Preview(showBackground = true , uiMode = UI_MODE_NIGHT_YES)
@Composable
fun searchBar() {
    ComposeNewsAppTheme {
        SearchBarView(Modifier,"",true,{},{},{})
    }
}
