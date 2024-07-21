package com.example.composenewsapp.news.navigation

sealed class Route(val route:String){

    //Onboarding
    data object AppStartNavigation:Route("appStartNavigation")
    data object OnboardingScreen: Route("onBoardingScreen")

    //Base App Module
    data object AppModuleNavigation:Route("appModuleNavigation")

    //News Module
    data object NewsNavigationScreen:Route("newsNavigation")
    data object NewsNavigatorScreen:Route("newsNavigator")
    data object HomeScreen:Route("homeScreen")
    data object BookMarkScreen:Route("bookMarkScreen")
    data object DetailScreen:Route("detailScreen")


}




//sealed class CreditLineVerifyOtp{
//    object Loading : CreditLineVerifyOtp()
//    data class  Success(val creditLineForm : com.etmoney.loan.creditline.data.source.local.entity.CreditLineForm) : CreditLineVerifyOtp()
//    data class Error(val errorMessage : String,var otpError : OtpResponseError?) : CreditLineVerifyOtp()
//}