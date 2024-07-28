package com.example.rainbow.offGridModule.offGrid.data.repo.impl

import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import com.example.rainbow.offGridModule.offGrid.data.repo.OffGridMusicRepository
import com.example.rainbow.offGridModule.offGrid.datamodel.SongData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OffGridMusicRepositoryImpl @Inject constructor() : OffGridMusicRepository {

//    override suspend fun getAllMusicList(context: Context): List<SongData>? {
//        return withContext(Dispatchers.IO) {
//            try {
//                val songList = mutableListOf<SongData>()
//                val mediaUrls = mutableListOf<Uri>()
//                val contentResolver: ContentResolver = context.contentResolver
//
//                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
//                    val mediaUrl1: Uri = MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
//                    val mediaUrl2: Uri = MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_INTERNAL)
//                    mediaUrls.add(mediaUrl1)
//                    mediaUrls.add(mediaUrl2)
//                } else {
//                    val mediaUrl1: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
//                    val mediaUrl2: Uri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI
//                    mediaUrls.add(mediaUrl1)
//                    mediaUrls.add(mediaUrl2)
//                }
//
//                val projection = arrayOf(
//                    MediaStore.Audio.Media._ID,
//                    MediaStore.Audio.Media.DISPLAY_NAME,
//                    MediaStore.Audio.Media.DURATION,
//                    MediaStore.Audio.Media.ARTIST,
//                    MediaStore.Audio.Media.ALBUM_ID
//                )
//                val sortOrder = MediaStore.Audio.Media.DATE_ADDED + " DESC"
//                val selection = "${MediaStore.Audio.Media.MIME_TYPE}=? AND ${MediaStore.Audio.Media.DISPLAY_NAME} LIKE ?"
//                val selectionArgs = arrayOf("audio/mpeg", "%.mp3")
//
//                mediaUrls.forEach { uri ->
//                    val cursor = contentResolver.query(uri, projection, selection, selectionArgs, sortOrder)
//                    cursor?.use {
//                        val idColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
//                        val nameColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)
//                        val durationColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
//                        val artistNameColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
//                        val albumIdColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)
//
//                        while (it.moveToNext()) {
//                            val id = it.getLong(idColumn)
//                            var name = it.getString(nameColumn)
//                            val duration = it.getLong(durationColumn)
//                            val artistName = it.getString(artistNameColumn)
//                            val albumId = it.getLong(albumIdColumn)
//
//                            Log.d("MusicList", "ID: $id, AlbumID: $albumId")
//
//                            val uriMusic = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id)
//                            val albumArtWork = if (albumId > 0) {
//                                ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), albumId)
//                            } else {
//                                null
//                            }
//
//                            Log.d("MusicList", "Album Artwork URI: $albumArtWork")
//
//                            name = name.substringBeforeLast(".", name)  // Ensures proper substring handling
//
//                            if (uriMusic != null) {
//                                val songData = SongData(
//                                    name,
//                                    artistName,
//                                    duration,
//                                    uriMusic,
//                                    albumArtWork,
//                                )
//                                songList.add(songData)
//                            }
//                        }
//                    } ?: Log.d("getAllMusicList", "Cursor is null for URI: $uri")
//                }
//                return@withContext songList
//            } catch (e: Exception) {
//                Log.d("getAllMusicList", "Exception: ${e.localizedMessage}")
//                return@withContext null
//            }
//        }
//        }

    override suspend fun getAllMusicList(context: Context): List<SongData>? {
        return withContext(Dispatchers.IO){
            try {
                val songList = mutableListOf<SongData>()
                var mediaUrl = mutableListOf<Uri>()
                val contentResolver: ContentResolver = context.contentResolver

                if(Build.VERSION.SDK_INT> Build.VERSION_CODES.Q){
                    val mediaUrl1: Uri = MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
                    val mediaUrl2: Uri = MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_INTERNAL)
                    mediaUrl.add(mediaUrl1)
                    mediaUrl.add(mediaUrl2)
                }else{
                    val mediaUrl1: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                    val mediaUrl2: Uri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI
                    mediaUrl.add(mediaUrl1)
                    mediaUrl.add(mediaUrl2)
                }
                val projection = arrayOf(
                    MediaStore.Audio.Media._ID,
                    MediaStore.Audio.Media.DISPLAY_NAME,
                    MediaStore.Audio.Media.DURATION,
                    MediaStore.Audio.Media.ALBUM_ARTIST,
                    MediaStore.Audio.Media.ALBUM_ID
                )
                val sortOrder= MediaStore.Audio.Media.DATE_ADDED+" DESC"
                val selection = "${MediaStore.Audio.Media.MIME_TYPE}=? AND ${MediaStore.Audio.Media.DISPLAY_NAME} LIKE ?"
                val selectionArgs = arrayOf("audio/mpeg", "%.mp3")
                mediaUrl.forEach {
                    val cursor = contentResolver.query(it,projection,selection,selectionArgs,sortOrder)
                    cursor?.use {
                        val idColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
                        val nameColumn= it.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)
                        val durationColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
                        val artistNameColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ARTIST)
                        val albumIdColumn= it.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)


                        while (cursor.moveToNext()){
                            val id = cursor.getLong(idColumn)
                            var name = cursor.getString(nameColumn)
                            val duration = cursor.getLong(durationColumn)
                            val artistName = cursor.getString(artistNameColumn)
                            val albumId = cursor.getLong(albumIdColumn)

                            val uriMusic = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,id)
//                            val albumArtWork = ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"),albumId)

                            val albumArtWork = if (albumId > 0) {
                                ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), albumId)
                            } else {
                                null
                            }
                            name?.let {
                                name = name.substring(0, it.lastIndexOf("."))
                            }

                            if(uriMusic!=null){
                                val songData = SongData(
                                    name,
                                    artistName,
                                    duration,
                                    uriMusic,
                                    albumArtWork,
                                )
                                songList.add(songData)
                            }

                        }

                    }
                }
                return@withContext songList
            }catch (e:Exception){
                Log.d("getAllMusicList",e.localizedMessage)
            }
            return@withContext null
        }
    }
}