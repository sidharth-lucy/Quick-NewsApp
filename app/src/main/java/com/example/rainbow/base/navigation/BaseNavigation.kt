package com.example.rainbow.base.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.composenewsapp.news.screen.OnboardingScreen

@Composable
fun BaseNavGraph(startDestination:String,saveAppStartData: () -> Unit) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = BaseRoute.AppStartNavigation.route,
            startDestination = BaseRoute.OnboardingScreen.route
        ){
            composable(route= BaseRoute.OnboardingScreen.route){
                OnboardingScreen(pageCount = 3,saveAppStartData)
            }
        }

        navigation(
            route = BaseRoute.AppModuleNavigation.route,
            startDestination= BaseRoute.AppModuleNavigationScreen.route
        ){
            composable(route=BaseRoute.AppModuleNavigationScreen.route){
                AppModuleBaseNavigator()
            }
        }
    }
}