package com.example.rainbow.offGridModule.offGrid.datamodel

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SongData(
    val name:String= "OffGrid",
    val extraData:String?,
    val duration:Long?,
    val uriSong: Uri,
    val uriSongImage: Uri?=null
): Parcelable {

}
