package com.example.composenewsapp.news.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.example.rainbow.base.navigation.BaseNavGraph
import com.example.rainbow.base.viewmodel.MainActivityViewModel
import com.example.rainbow.baseUtils.AppPermissionHandler
import com.example.rainbow.ui.theme.ComposeNewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this)[MainActivityViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
//        WindowCompat.setDecorFitsSystemWindows(window,false)
//        enableEdgeToEdge()
        viewModel.getUserAppEntry()
        getUserPermission()
        observe()
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                viewModel.splashCondition.value
            }
        }
        setContent {
            ComposeNewsAppTheme {
                Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
                    val startDestination = viewModel.startDestination
                    BaseNavGraph(startDestination =startDestination.value ){
                        viewModel.saveUserAppEntry(true)
                    }
                }
            }
        }
    }

    private fun observe(){
        viewModel.userDataLiveData.observe(this){

        }
    }

    private fun getUserPermission(){
        AppPermissionHandler.getUserAudioFileReadingPermission(this, this)
    }
}
