package com.example.rainbow.offGridModule.offGrid.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rainbow.base.viewmodel.RainbowResult
import com.example.rainbow.baseUtils.Constants
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
                LaunchedEffect(musicControlViewModel.isPlaying.value) {
                    while (musicControlViewModel.isPlaying.value) {
                        currentPosition.value = musicControlViewModel.getSongProgressData()
                        delay(1000L)
                    }
                }
                val currSongToPlay = remember {
                    mutableStateOf(song)
                }
                OffGridMusicPlayingScreen(modifier = Modifier,
                    songData = currSongToPlay.value,
                    data = musicData,
                    playState = currentPosition.value,
                    topNavigationButtonAction = { navType ->
                        handleNavigationButtonClick(navType, navController, appModuleMenuClicked)
                    }, onMusicControlClicked = {action->
                        val preVState = currentPosition.value
                        handleMusicActionClick(action,musicControlViewModel,musicData,currSongToPlay)
                        preVState.playState=musicControlViewModel.isPlaying.value
                        currentPosition.value = preVState
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
                }, onSongClick = {song,indexSong->
                    musicControlViewModel.playNewSong(song,indexSong)
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

private fun handleMusicActionClick(
    action: MusicControlAction,
    musicControlViewModel: MusicControlViewModel,
    data: List<SongData>,
    currSongData:MutableState<SongData>
) {
    when (action) {
        is MusicControlAction.PlayNextMusic -> {
            if (musicControlViewModel.activeMusicIndex.value != null && musicControlViewModel.activeMusicIndex.value!! + 1 < data.size) {
                currSongData.value = data.get(musicControlViewModel.activeMusicIndex.value!! + 1)
                musicControlViewModel.playNewSong(
                    data.get(musicControlViewModel.activeMusicIndex.value!! + 1),
                    musicControlViewModel.activeMusicIndex.value!! + 1
                )
            }
        }

        is MusicControlAction.PlayPreviousMusic -> {
            if (musicControlViewModel.activeMusicIndex.value != null && musicControlViewModel.activeMusicIndex.value!! >0) {
                currSongData.value = data.get(musicControlViewModel.activeMusicIndex.value!! - 1)
                musicControlViewModel.playNewSong(
                    data.get(musicControlViewModel.activeMusicIndex.value!! - 1),
                    musicControlViewModel.activeMusicIndex.value!! - 1
                )
            }
        }

        is MusicControlAction.PlayPauseMusic -> {
            if (musicControlViewModel.isPlaying.value) {
                musicControlViewModel.pauseSong()
            } else {
                musicControlViewModel.ResumeOrStart()
            }
        }
    }
}

private fun navigateToMusicPlayScreen(navController: NavController,route: OffGridRoute,songData: SongData){
    navController.currentBackStackEntry?.savedStateHandle?.set(Constants.SONG_TO_BE_PLAYED,songData)
    navController.navigate(route= route.route)
}
