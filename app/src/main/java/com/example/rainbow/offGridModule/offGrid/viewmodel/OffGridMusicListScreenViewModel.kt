package com.example.rainbow.offGridModule.offGrid.viewmodel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rainbow.base.viewmodel.RainbowResult
import com.example.rainbow.offGridModule.offGrid.data.repo.OffGridMusicRepository
import com.example.rainbow.offGridModule.offGrid.datamodel.SongData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OffGridMusicListScreenViewModel @Inject constructor(
    private val musicRepository: OffGridMusicRepository
):ViewModel() {

    private var _allSongList = mutableStateOf<RainbowResult<List<SongData>,String>>(RainbowResult.Loading)
    val allSongList : State<RainbowResult<List<SongData>,String>> = _allSongList

    fun getAllMusicList(context: Context){
        viewModelScope.launch(Dispatchers.IO) {
            _allSongList.value = RainbowResult.Loading
            val allSongData = musicRepository.getAllMusicList(context)
            if(allSongData ==null){
                _allSongList.value = RainbowResult.Failure("Error While Loading Music!")
            }else if(allSongData.size>0){
                _allSongList.value = RainbowResult.Success(allSongData)
            }else{
                _allSongList.value = RainbowResult.Failure("No Any Offline Song Available!")
            }

        }
    }

}