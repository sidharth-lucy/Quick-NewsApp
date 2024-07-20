package com.example.rainbow.baseUtils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object AppPermissionHandler {
    private val STORAGE_PERMISSION_REQUEST_CODE = 1001
    fun getUserAudioFileReadingPermission(context: Context,activity:Activity):Boolean{
        if(ContextCompat.checkSelfPermission(context,Manifest.permission.READ_MEDIA_AUDIO) == PackageManager.PERMISSION_GRANTED){
            return true
        }else{
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.READ_MEDIA_AUDIO),
                STORAGE_PERMISSION_REQUEST_CODE)
            return false
        }
    }

}