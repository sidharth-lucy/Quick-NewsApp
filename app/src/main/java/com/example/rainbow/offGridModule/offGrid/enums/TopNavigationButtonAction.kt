package com.example.rainbow.offGridModule.offGrid.enums

sealed class TopNavigationButtonAction {
    data object NavigationBackButtonAction:TopNavigationButtonAction()
    data object NavigationMoreButtonAction:TopNavigationButtonAction()
    data object NavigationOtherModuleAction:TopNavigationButtonAction()
}