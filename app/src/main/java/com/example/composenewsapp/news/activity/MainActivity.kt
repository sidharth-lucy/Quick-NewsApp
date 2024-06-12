package com.example.composenewsapp.news.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.composenewsapp.data.PrefDataStore
import com.example.composenewsapp.data.local.dao.NewsArticleDao
import com.example.composenewsapp.data.local.dao.NewsDataBase
import com.example.composenewsapp.news.navigation.NavGraph
import com.example.composenewsapp.news.remote.response.Article
import com.example.composenewsapp.news.remote.response.Source
import com.example.composenewsapp.news.screen.OnboardingScreen
import com.example.composenewsapp.news.viewmodel.MainActivityViewModel
import com.example.composenewsapp.ui.theme.ComposeNewsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel:MainActivityViewModel by lazy {
        ViewModelProvider(this)[MainActivityViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        WindowCompat.setDecorFitsSystemWindows(window,false)
        viewModel.getUserAppEntry()
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
                    NavGraph(startDestination =startDestination.value ){
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

}
