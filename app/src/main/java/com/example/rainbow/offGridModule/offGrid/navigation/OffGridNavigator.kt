package com.example.rainbow.offGridModule.offGrid.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rainbow.base.viewmodel.RainbowResult
import com.example.rainbow.baseUtils.Constants
import com.example.rainbow.newsAppModule.news.remote.response.Article
import com.example.rainbow.offGridModule.offGrid.datamodel.SongData
import com.example.rainbow.offGridModule.offGrid.datamodel.SongProgressData
import com.example.rainbow.offGridModule.offGrid.enums.MusicControlAction
import com.example.rainbow.offGridModule.offGrid.enums.TopNavigationButtonAction
import com.example.rainbow.offGridModule.offGrid.screen.OffGridMusicListScreen
import com.example.rainbow.offGridModule.offGrid.screen.OffGridMusicPlayingScreen
import com.example.rainbow.offGridModule.offGrid.viewmodel.MusicControlViewModel
import com.example.rainbow.offGridModule.offGrid.viewmodel.OffGridMusicListScreenViewModel
import kotlinx.coroutines.delay


@Composable
fun OffGridNavigator(musicControlViewModel: MusicControlViewModel,
                     viewmodel: OffGridMusicListScreenViewModel,
                     appModuleMenuClicked:()->Unit) {

    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = OffGridRoute.OffGridMusicListScreen.route
    ) {

        composable(route = OffGridRoute.OffGridMusicPlayScreen.route) {
            var musicData = emptyList<SongData>()
            viewmodel.allSongList.value.let { it->
                if(it is RainbowResult.Success){
                    musicData= it.data
                }
            }
            val currentPosition = remember {
                mutableStateOf(SongProgressData(false,0f,0f))
            }
            navController.previousBackStackEntry?.savedStateHandle?.get<SongData>(key = Constants.SONG_TO_BE_PLAYED)?.let { song->
                LaunchedEffect(true) {
                    while (musicControlViewModel.isPlaying.value) {
                        currentPosition.value = musicControlViewModel.getSongProgressData()
                        delay(1000L)
                    }
                }
                OffGridMusicPlayingScreen(modifier = Modifier,
                    songData = song,
                    data = musicData,
                    playState = currentPosition.value,
                    topNavigationButtonAction = { navType ->
                        handleNavigationButtonClick(navType, navController, appModuleMenuClicked)
                    }, onMusicControlClicked = {action->
                        handleMusicActionClick(action)
                    })
            }
        }

        composable(route = OffGridRoute.OffGridMusicListScreen.route) {
            val context = LocalContext.current
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
                    musicControlViewModel.playSong(song.uriSong)
                    navigateToMusicPlayScreen(navController=navController, route = OffGridRoute.OffGridMusicPlayScreen, songData = song)
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

private fun navigateToMusicPlayScreen(navController: NavController,route: OffGridRoute,songData: SongData){
    navController.currentBackStackEntry?.savedStateHandle?.set(Constants.SONG_TO_BE_PLAYED,songData)
    navController.navigate(route= route.route)
}
