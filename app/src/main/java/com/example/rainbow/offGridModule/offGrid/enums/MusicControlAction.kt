package com.example.rainbow.offGridModule.offGrid.enums

import com.example.rainbow.offGridModule.offGrid.datamodel.SongData


sealed class MusicControlAction{
    data object PlayPauseMusic : MusicControlAction()
    data object PlayPreviousMusic : MusicControlAction()
    data object PlayNextMusic : MusicControlAction()
}