package com.example.rainbow.base.navigation

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composenewsapp.news.navigation.NewsNavigator
import com.example.rainbow.offGridModule.offGrid.navigation.OffGridNavigator
import com.example.rainbow.offGridModule.offGrid.viewmodel.MusicControlViewModel
import com.example.rainbow.offGridModule.offGrid.viewmodel.OffGridMusicListScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun AppModuleBaseNavigator() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val selectedApp = rememberSaveable {
        mutableStateOf(AppModuleType.NEWS_APP_MODULE)
    }
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        modifier = Modifier,
        drawerState = drawerState,
        drawerContent = {
            NavigationDrawerContent(
                modifier = Modifier
                    .requiredWidth(width = 270.dp)
                    .statusBarsPadding()
                    .systemBarsPadding(),
                selectedApp = selectedApp.value,
                newSelectedModule = {module->
                    selectedApp.value = module
                    scope.launch {
                        drawerState.close()
                    }
                })
        }
    ) {
        when(selectedApp.value){
            AppModuleType.NEWS_APP_MODULE->{
                NewsNavigator(appModuleMenuClicked = {
                    handleAppModuleMenu(scope,drawerState)
                })
            }
            AppModuleType.MUSIC_APP_MODULE->{
                val musicControlViewModel: MusicControlViewModel = hiltViewModel()
                val viewmodel: OffGridMusicListScreenViewModel = hiltViewModel()
                val context = LocalContext.current
                DisposableEffect(Unit) {
                    musicControlViewModel.bindService(context)
                    onDispose {
                        musicControlViewModel.unbindService(context)
                    }
                }
                OffGridNavigator(musicControlViewModel,viewmodel,appModuleMenuClicked = {
                    handleAppModuleMenu(scope,drawerState)
                })
            }
        }
    }
}

private fun handleAppModuleMenu(scope:CoroutineScope,drawerState:DrawerState){
    scope.launch {
        drawerState.open()
    }
}