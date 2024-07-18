package com.example.composenewsapp.news.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.composenewsapp.news.screen.HomeScreenpage
import com.example.composenewsapp.news.screen.OnboardingScreen
import com.example.composenewsapp.news.viewmodel.HomeViewModel


@Composable
fun NavGraph(startDestination: String, saveAppStartData: () -> Unit) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnboardingScreen.route
        ) {
            composable(route = Route.OnboardingScreen.route) {
                OnboardingScreen(pageCount = 3, saveAppStartData)
            }
        }

        navigation(
            route = Route.NewsNavigationScreen.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {
            composable(route = Route.NewsNavigatorScreen.route) {
                NewsNavigator()
            }

        }
    }

}


