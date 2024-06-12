package com.example.composenewsapp.news.navigation

sealed class Route(val route:String){

    data object OnboardingScreen: Route("onBoardingScreen")
    data object HomeScreen:Route("homeScreen")
    data object SearchScreen:Route("searchScreen")
    data object BookMarkScreen:Route("bookMarkScreen")
    data object DetailScreen:Route("detailScreen")
    data object AppStartNavigation:Route("appStartNavigation")
    data object NewsNavigationScreen:Route("newsNavigation")
    data object NewsNavigatorScreen:Route("newsNavigator")

}




//sealed class CreditLineVerifyOtp{
//    object Loading : CreditLineVerifyOtp()
//    data class  Success(val creditLineForm : com.etmoney.loan.creditline.data.source.local.entity.CreditLineForm) : CreditLineVerifyOtp()
//    data class Error(val errorMessage : String,var otpError : OtpResponseError?) : CreditLineVerifyOtp()
//}