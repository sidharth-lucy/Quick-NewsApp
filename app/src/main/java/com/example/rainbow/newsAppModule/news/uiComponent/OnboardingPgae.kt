package com.example.composenewsapp.news.uiComponent

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composenewsapp.R
import com.example.composenewsapp.news.datamodel.Page
import com.example.rainbow.ui.theme.ComposeNewsAppTheme


@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = false, showSystemUi = false)
@Composable
fun Preview() {
    ComposeNewsAppTheme {
        OnboardingPageView(Modifier, onboardingPages[1])
    }
}


@Composable
fun OnboardingPageView(modifier: Modifier, pageData: Page) {
    Column(modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = pageData.image),
            contentDescription = "onboarding"
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = pageData.title,
            modifier = Modifier
                .padding(horizontal = 16.dp),
            style = MaterialTheme.typography.displaySmall.copy(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            ),
            color = colorResource(id = R.color.display_small)
        )
        Text(
            text = pageData.desc,
            modifier = Modifier
                .padding(horizontal = 16.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.text_medium)
        )
    }
}


val onboardingPages = listOf(
    Page(
        title = "Stay Informed",
        desc = "Get the latest news and updates from around the world.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Personalized Experience",
        desc = "Customize your news feed to see what matters most to you.",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Breaking News Alerts",
        desc = "Receive instant notifications for breaking news and important updates.",
        image = R.drawable.onboarding3
    )
)
