package com.example.rainbow.base.viewmodel

import android.net.Uri
import com.example.rainbow.offGridModule.offGrid.datamodel.SongData

val cc = SongData(
    id= 1,
    name = "Chill Vibes",
    extraData = "Relaxing Tunes",
    duration = 240000, // 4 minutes in milliseconds
    uriSong = Uri.parse(""),
)


val data = listOf(
    SongData(
        id= 1,
        name = "OffGriddvd",
        extraData = "Electro Beat",
        duration = 180000, // 3 minutes in milliseconds
        uriSong = Uri.parse("https://images.unsplash.com/photo-1525362081669-2b476bb628c3?q=80&w=3456&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),

        ),
    SongData(
        id= 1,
        name = "Chill Vibes Chill Vibes Chill Vibes",
        extraData = "Relaxing Tunes",
        duration = 240000, // 4 minutes in milliseconds
        uriSong = Uri.parse(""),
    ),
    SongData(
        id= 1,
        name = "Workout Mixcd",
        extraData = "High Energy",
        duration = 300000, // 5 minutes in milliseconds
        uriSong = Uri.parse("https://example.com/songs/offgrid.mp3"),
    ),
    SongData(
        id= 1,
        name = "Evening Jazzrr",
        extraData = "Smooth Jazz",
        duration = 360000, // 6 minutes in milliseconds
        uriSong = Uri.parse("https://example.com/songs/offgrid.mp3"),
    ),SongData(
        id= 1,
        name = "OffGridChill Vibes",
        extraData = "Electro Beat Chill Vibes Chill Vibes",
        duration = 180000, // 3 minutes in milliseconds
        uriSong = Uri.parse("https://images.unsplash.com/photo-1525362081669-2b476bb628c3?q=80&w=3456&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),

        ),)