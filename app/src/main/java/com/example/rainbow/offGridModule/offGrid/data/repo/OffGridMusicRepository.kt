package com.example.rainbow.offGridModule.offGrid.data.repo

import android.content.Context
import com.example.rainbow.offGridModule.offGrid.datamodel.SongData

interface OffGridMusicRepository {
    suspend fun getAllMusicList(context: Context):List<SongData>?
}