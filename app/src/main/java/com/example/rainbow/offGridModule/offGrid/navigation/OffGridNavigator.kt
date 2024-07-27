package com.example.rainbow.offGridModule.offGrid.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rainbow.offGridModule.offGrid.enums.TopNavigationButtonAction
import com.example.rainbow.offGridModule.offGrid.screen.OffGridMusicPlayingScreen


@Composable
fun OffGridNavigator() {

    val navController = rememberNavController()


    NavHost(navController = navController,
        startDestination = OffGridRoute.OffGridMusicPlayScreen.route) {

        composable(route = OffGridRoute.OffGridMusicPlayScreen.route){
            OffGridMusicPlayingScreen(modifier = Modifier, topNavigationButtonAction = {navType->
                when(navType){
                    TopNavigationButtonAction.NavigationMoreButtonAction->{

                    }
                    TopNavigationButtonAction.NavigationBackButtonAction->{
                        navController.navigateUp()
                    }
                }
            })
        }

    }

}