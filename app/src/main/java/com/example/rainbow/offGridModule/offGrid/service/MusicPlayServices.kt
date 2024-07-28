package com.example.rainbow.offGridModule.offGrid.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import androidx.core.app.NotificationCompat
import android.support.v4.media.session.MediaSessionCompat
import com.example.composenewsapp.R
import com.example.rainbow.baseUtils.Constants
import com.example.rainbow.offGridModule.offGrid.datamodel.SongData

class MusicPlayService : Service() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var mediaSession: MediaSessionCompat
    private val binder = MusicBinder()
    var serviceListener: MusicServiceCallback? = null

    inner class MusicBinder : Binder() {
        fun getService(serviceListener: MusicServiceCallback): MusicPlayService {
            this@MusicPlayService.serviceListener = serviceListener
            return this@MusicPlayService
        }
    }

    override fun onBind(intent: Intent?): IBinder {
        mediaSession = MediaSessionCompat(this, "My Music")
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    fun createMediaPlayer(song: Uri) {
        mediaPlayer.reset()
        mediaPlayer.setDataSource(this, song)
        mediaPlayer.setOnPreparedListener {
            serviceListener?.onServiceBind(true)
            mediaPlayer.start()
        }
        mediaPlayer.setOnErrorListener { _, _, _ ->
            serviceListener?.onServiceBind(false)
            false
        }
        mediaPlayer.setOnCompletionListener {
            serviceListener?.onPlaybackEnd()
        }
        mediaPlayer.prepareAsync()
    }

    fun playSong() {
        if (!mediaPlayer.isPlaying) {
            mediaPlayer.start()
        }
    }

    fun pauseSong() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        }
    }

    fun stopSong() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
    }

    fun setLooping(loop: Boolean) {
        mediaPlayer.isLooping = loop
    }

    fun getMusicDuration(): Int? {
        return if (mediaPlayer.isPlaying) mediaPlayer.duration else null
    }

    fun getCurrentPosition(): Int? {
        return if (mediaPlayer.isPlaying) mediaPlayer.currentPosition else null
    }

    fun seekTo(position: Int) {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.seekTo(position)
        }
    }

    fun showNotification(music: SongData) {
        val notification = NotificationCompat.Builder(this, Constants.NOTIFICATION_CHANNEL_ID)
            .setContentTitle(music.name)
            .setContentText(music.extraData ?: "")
            .setSmallIcon(R.drawable.ic_music_item_default_1)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_play_icon))
//            .setStyle(MediaStyle().setMediaSession(mediaSession.sessionToken))
            .setStyle(androidx.media.app.NotificationCompat.MediaStyle().setMediaSession(mediaSession.sessionToken))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setOnlyAlertOnce(true)
            .build()

        startForeground(Constants.FOREGROUND_SERVICE_ID, notification)
    }
}


interface MusicServiceCallback {
    fun onServiceBind(binds: Boolean)
    fun onPlaybackEnd()
}