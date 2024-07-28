package com.example.rainbow.baseUtils

object TimeUtility {

    fun getMilliToMinuteSecond(timeInMilli:Long):String{
        val inSecond = timeInMilli/1000
        val minute = inSecond/60
        val second = inSecond%60
        var time = ""

        if(minute==0L){
            time+= "00"
        } else if(minute<10){
            time+= "0${minute}"
        } else{
            time+= "${minute}"
        }
        if(second==0L){
            time+= ":00"
        } else if(second<10){
            time+= ":0${second}"
        } else{
            time+= ":${second}"
        }
        return time

    }
}