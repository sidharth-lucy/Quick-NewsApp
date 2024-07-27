package com.example.composenewsapp.news.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.composenewsapp.news.screen.OnboardingScreen
import com.example.rainbow.base.navigation.BaseRoute


@Composable
fun NavGraph(startDestination: String, saveAppStartData: () -> Unit) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = BaseRoute.AppStartNavigation.route,
            startDestination = BaseRoute.OnboardingScreen.route
        ) {
            composable(route = BaseRoute.OnboardingScreen.route) {
                OnboardingScreen(pageCount = 3, saveAppStartData)
            }
        }

        navigation(
            route = NewAppRoute.NewsNavigationScreen.route,
            startDestination = NewAppRoute.NewsNavigatorScreen.route
        ) {
            composable(route = NewAppRoute.NewsNavigatorScreen.route) {
                NewsNavigator(appModuleMenuClicked = {
                    
                })
            }

        }
    }

}


