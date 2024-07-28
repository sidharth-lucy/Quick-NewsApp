package com.example.rainbow.baseUtils

import android.content.Context
import android.util.Log
import com.example.rainbow.offGridModule.offGrid.datamodel.SongData


object Tester {
    fun checkInputStream(context: Context, songData: SongData){

        try {
            val inputStream = context.contentResolver.openInputStream(songData.uriSongImage!!)
            if (inputStream != null) {
                Log.d("URI Check", "Successfully opened input stream")
                inputStream.close()
            } else {
                Log.d("URI Check", "Failed to open input stream")
            }
        } catch (e: Exception) {
            Log.d("URI Check", "Error opening input stream: ${e.message}")
        }

    }
}

