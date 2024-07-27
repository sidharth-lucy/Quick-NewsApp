package com.example.rainbow.base.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rainbow.base.navigation.BaseRoute
import com.example.rainbow.newsAppModule.data.PrefDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val prefDataStore: PrefDataStore):ViewModel() {

    private var _userDataLiveData:MutableLiveData<Boolean> = MutableLiveData()
    val userDataLiveData:MutableLiveData<Boolean> = _userDataLiveData

    private val _splashCondition = mutableStateOf(true)
    val splashCondition:State<Boolean> = _splashCondition

    var startDestination = mutableStateOf(BaseRoute.AppStartNavigation.route)
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
                    startDestination.value = BaseRoute.AppModuleNavigation.route
                }else{
                    startDestination.value = BaseRoute.AppStartNavigation.route
                }
                delay(300)
                _splashCondition.value = false
            }
        }

    }
}