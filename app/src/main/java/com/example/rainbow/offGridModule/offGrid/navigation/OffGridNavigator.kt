package com.example.rainbow.offGridModule.offGrid.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController


@Composable
fun OffGridNavigator(modifier: Modifier = Modifier) {

    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "") {

    }

}