package com.example.rainbow.base.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.composenewsapp.news.navigation.NewsNavigator
import com.example.composenewsapp.news.navigation.Route
import com.example.composenewsapp.news.screen.OnboardingScreen

@Composable
fun BaseNavGraph(startDestination:String,saveAppStartData: () -> Unit) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnboardingScreen.route
        ){
            composable(route= Route.OnboardingScreen.route){
                OnboardingScreen(pageCount = 3,saveAppStartData)
            }
        }

        navigation(
            route = Route.AppModuleNavigation.route,
            startDestination= Route.AppModuleNavigation.route
        ){
            composable(route=Route.AppModuleNavigation.route){
                AppModuleBaseNavigator()
            }
        }
    }
}