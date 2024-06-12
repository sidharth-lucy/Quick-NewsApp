package com.example.composenewsapp.news.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.composenewsapp.news.uiComponent.NewsButton
import com.example.composenewsapp.news.uiComponent.NewsPreviousButton
import com.example.composenewsapp.news.uiComponent.OnboardingPageView
import com.example.composenewsapp.news.uiComponent.PageIndicator
import com.example.composenewsapp.news.uiComponent.onboardingPages
import kotlinx.coroutines.launch
import values.Dimens


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(pageCount: Int, onGetStartedClicked:()->Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(initialPage = 0) {
            pageCount
        }

        val buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> {
                        return@derivedStateOf Pair("", "Next")
                    }

                    pageCount - 1 -> {
                        return@derivedStateOf Pair("Previous", "Get started")
                    }

                    else -> {
                        return@derivedStateOf Pair("Previous", "Next")
                    }
                }
            }
        }
        HorizontalPager(state = pagerState) { pageNo ->
            OnboardingPageView(modifier = Modifier, pageData = onboardingPages[pageNo])
        }
        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.padding_16)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PageIndicator(
                modifier = Modifier,
                size = Dimens.padding_13,
                pageCount = pageCount,
                selectedPage = pagerState.currentPage,
                selectedColor = MaterialTheme.colorScheme.primary,
                unSelectedColor = Color.LightGray
            )

            Row(
                modifier = Modifier.padding(bottom = Dimens.padding_10),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Dimens.padding_5)
            ) {
                val scope = rememberCoroutineScope()
                if (pagerState.currentPage != 0) {
                    NewsPreviousButton(Modifier, buttonState.value.first) {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                }
                NewsButton(
                    Modifier.animateContentSize(),
                    Dimens.padding_8,
                    buttonState.value.second
                ) {
                    scope.launch {
                        if (pagerState.currentPage + 1 == pageCount) {
                            onGetStartedClicked()
                        } else {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                }

            }
        }
    }
}














