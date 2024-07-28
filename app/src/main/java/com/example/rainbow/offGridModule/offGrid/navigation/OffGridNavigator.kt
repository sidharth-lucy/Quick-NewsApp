package com.example.rainbow.offGridModule.offGrid.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rainbow.offGridModule.offGrid.enums.MusicControlAction
import com.example.rainbow.offGridModule.offGrid.enums.TopNavigationButtonAction
import com.example.rainbow.offGridModule.offGrid.screen.OffGridMusicListScreen
import com.example.rainbow.offGridModule.offGrid.screen.OffGridMusicPlayingScreen
import com.example.rainbow.offGridModule.offGrid.viewmodel.MusicControlViewModel
import com.example.rainbow.offGridModule.offGrid.viewmodel.OffGridMusicListScreenViewModel


@Composable
fun OffGridNavigator(appModuleMenuClicked:()->Unit) {

    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = OffGridRoute.OffGridMusicListScreen.route
    ) {

        composable(route = OffGridRoute.OffGridMusicPlayScreen.route) {
            OffGridMusicPlayingScreen(modifier = Modifier,
                topNavigationButtonAction = { navType ->
                    handleNavigationButtonClick(navType, navController, appModuleMenuClicked)
                }, onMusicControlClicked = {action->
                    handleMusicActionClick(action)
                })
        }

        composable(route = OffGridRoute.OffGridMusicListScreen.route) {
            val viewmodel: OffGridMusicListScreenViewModel = hiltViewModel()
            val musicControlViewModel:MusicControlViewModel = hiltViewModel()
            val context = LocalContext.current
            DisposableEffect(Unit) {
                musicControlViewModel.bindService(context)
                onDispose {
                    musicControlViewModel.unbindService(context)
                }
            }
            LaunchedEffect(key1 = true) {
                viewmodel.getAllMusicList(context)
            }
            OffGridMusicListScreen(
                modifier = Modifier,
                context = context,
                allSong = viewmodel.allSongList.value,
                topNavigationAction = {navType->
                    handleNavigationButtonClick(navType,navController,appModuleMenuClicked)
                }, onSongClick = {song->
                    //play pause song
                    musicControlViewModel.playSong(song.uriSong)
                })
        }

    }

}


private fun handleNavigationButtonClick(navType:TopNavigationButtonAction,navController: NavController,appModuleMenuClicked:()->Unit){
    when (navType) {
        TopNavigationButtonAction.NavigationMoreButtonAction -> {

        }

        TopNavigationButtonAction.NavigationBackButtonAction -> {
            navController.navigateUp()
        }

        TopNavigationButtonAction.NavigationOtherModuleAction -> {
            appModuleMenuClicked()
        }
    }
}

private fun handleMusicActionClick(action: MusicControlAction){
    when(action){
        is MusicControlAction.PlayNextMusic->{

        }
        is MusicControlAction.PlayPreviousMusic->{

        }
        is MusicControlAction.PlayPauseMusic->{

        }
    }
}