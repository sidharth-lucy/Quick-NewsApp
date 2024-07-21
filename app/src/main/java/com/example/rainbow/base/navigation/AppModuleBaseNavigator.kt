package com.example.rainbow.base.navigation

import android.util.Log
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import values.Dimens


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
                })
        }
    ) {
        if(selectedApp.value==AppModuleType.NEWS_APP_MODULE){
            Log.d("sksks",AppModuleType.NEWS_APP_MODULE.name)
        }else if(selectedApp.value==AppModuleType.MUSIC_APP_MODULE){
            Log.d("sksks",AppModuleType.MUSIC_APP_MODULE.name)
        }
        Scaffold(
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    text = { Text("Show drawer") },
                    icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                    onClick = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }
                )
            }
        ) { contentPadding ->
            // Screen content
        }
    }
}