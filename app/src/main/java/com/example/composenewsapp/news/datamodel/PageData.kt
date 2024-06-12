package com.example.composenewsapp.news.datamodel

import androidx.annotation.DrawableRes
import com.example.composenewsapp.R

data class Page(
    val title:String,
    val desc:String,
    @DrawableRes val image:Int
)

