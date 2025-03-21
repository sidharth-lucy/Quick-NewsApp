package com.example.rainbow.offGridModule.offGrid.viewmodel

import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.net.Uri
import android.os.IBinder
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.example.rainbow.offGridModule.offGrid.datamodel.SongData
import com.example.rainbow.offGridModule.offGrid.datamodel.SongProgressData
import com.example.rainbow.offGridModule.offGrid.service.MusicPlayService
import com.example.rainbow.offGridModule.offGrid.service.MusicServiceCallback
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.ref.WeakReference
import javax.inject.Inject


@HiltViewModel
class MusicControlViewModel @Inject constructor(application: Application): AndroidViewModel(application), MusicServiceCallback {

    private var musicPlayService: WeakReference<MusicPlayService>? = null
    val isServiceBound = mutableStateOf(false)
    val isPlaying = mutableStateOf(false)
    var activeMusic:MutableState<SongData?> = mutableStateOf(null)
    var activeMusicIndex:MutableState<Int?> = mutableStateOf(null)

    private val serviceConnection = object : ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MusicPlayService.MusicBinder
            musicPlayService = WeakReference(binder.getService(this@MusicControlViewModel))
            isServiceBound.value = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            musicPlayService = null
            isServiceBound.value = false
        }
    }

    override fun onServiceBind(binds: Boolean) {
        isPlaying.value = binds
    }

    override fun onPlaybackEnd() {
        isPlaying.value = false
    }


    fun bindService(context: Context) {
        val intent = Intent(context, MusicPlayService::class.java)
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    fun unbindService(context: Context) {
        if (isServiceBound.value) {
            context.unbindService(serviceConnection)
            isServiceBound.value = false
        }
    }

    fun playNewSong(song: SongData,indexOfSong:Int) {
        activeMusic.value = song
        activeMusicIndex.value = indexOfSong
        musicPlayService?.get()?.createMediaPlayer(song.uriSong)
        isPlaying.value = true
    }

    fun ResumeOrStart(){
        musicPlayService?.get()?.playSong()
        isPlaying.value = true
    }

    fun pauseSong() {
        musicPlayService?.get()?.pauseSong()
        isPlaying.value = false
    }

    fun stopSong() {
        musicPlayService?.get()?.stopSong()
        isPlaying.value = false
    }

    fun setLooping(loop: Boolean) {
        musicPlayService?.get()?.setLooping(loop)
    }


    fun getMusicDuration():Int?{
        return musicPlayService?.get()?.getMusicDuration()
    }

    fun getCurrentPosition():Int?{
        return musicPlayService?.get()?.getCurrentPosition()
    }

    fun getSongProgressData():SongProgressData{
        val isPlaying = isPlaying
        val progress = getCurrentPosition()
        val getMax = getMusicDuration()
        return SongProgressData(isPlaying.value, progress?.toFloat() ?:0f,getMax?.toFloat()?:0f)
    }

}