package com.example.composenewsapp.news.uiComponent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Preview(showBackground = true)
@Composable
fun showButton() {
    NewsButton(Modifier, 8.dp, "Next") {

    }
//    NewsPreviousButton(Modifier,"Back" ){
//
//    }
}

@Composable
fun NewsButton(
    modifier: Modifier,
    cornerRadius: Dp,
    buttonText: String,
    onButtonClick: () -> Unit
) {

    Button(onClick = {
        onButtonClick()
    }, modifier = modifier, colors = ButtonDefaults.buttonColors(
        contentColor = Color.White,
        containerColor = MaterialTheme.colorScheme.primary
    ),
        shape = RoundedCornerShape(size = cornerRadius)

    ) {
        Text(text = buttonText,
            style =  MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold ))
    }

}

@Composable
fun NewsPreviousButton(
    modifier: Modifier,
    buttonText: String,
    onButtonClick: () -> Unit
) {
    Text(modifier = modifier.clickable {
        onButtonClick()
    }, text = buttonText,
        style =  MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold ),
        color = Color.LightGray
    )
}


