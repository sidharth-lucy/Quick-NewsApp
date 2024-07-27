package com.example.rainbow.base.navigation

sealed class BaseRoute(val route:String) {
    //Onboarding
    data object AppStartNavigation: BaseRoute("appStartNavigation")
    data object OnboardingScreen: BaseRoute("onBoardingScreen")

    //Base App Module
    data object AppModuleNavigation: BaseRoute("appModuleNavigation")
    data object AppModuleNavigationScreen: BaseRoute("appModuleNavigationScreen")

}