package com.example.rainbow.offGridModule.offGrid.navigation

sealed class OffGridRoute(val route:String) {
    data object OffGridMusicListScreen:OffGridRoute("OffGridMusicListScreen")
    data object OffGridMusicPlayScreen:OffGridRoute("OffGridMusicPlayScreen")

}