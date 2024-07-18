package com.example.composenewsapp.news.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composenewsapp.news.navigation.Route
import com.example.rainbow.newsAppModule.data.PrefDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val prefDataStore: PrefDataStore):ViewModel() {

    private var _userDataLiveData:MutableLiveData<Boolean> = MutableLiveData()
    val userDataLiveData:MutableLiveData<Boolean> = _userDataLiveData

    private val _splashCondition = mutableStateOf(true)
    val splashCondition:State<Boolean> = _splashCondition

    var startDestination = mutableStateOf(Route.AppStartNavigation.route)
        private set

    fun saveUserAppEntry(data:Boolean){
        viewModelScope.launch(Dispatchers.IO){
            prefDataStore.saveAppOnboarding(data)
        }
    }

    fun getUserAppEntry(){
        viewModelScope.launch(Dispatchers.IO) {
            prefDataStore.getAppOnboarding().collect{
                _userDataLiveData.postValue(it)
                if(it){
                    startDestination.value = Route.NewsNavigationScreen.route
                }else{
                    startDestination.value = Route.AppStartNavigation.route
                }
                delay(300)
                _splashCondition.value = false
            }
        }

    }
}