package com.example.rainbow.base.viewmodel

sealed class RainbowResult<out T,out E> {
    data class Success<T>(var data:T):RainbowResult<T,Nothing>()
    data class Failure<E>(var error:E):RainbowResult<Nothing,E>()
    data object Loading:RainbowResult<Nothing,Nothing>()
}