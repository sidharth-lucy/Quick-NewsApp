package com.example.composenewsapp.news.navigation

sealed class NewAppRoute(val route:String){
    //News Module
    data object NewsNavigationScreen:NewAppRoute("newsNavigation")
    data object NewsNavigatorScreen:NewAppRoute("newsNavigator")
    data object HomeScreen:NewAppRoute("homeScreen")
    data object BookMarkScreen:NewAppRoute("bookMarkScreen")
    data object DetailScreen:NewAppRoute("detailScreen")
}

