package com.rie.mystore.ui.screen

sealed class MrScreen(val route: String) {
    object Home : MrScreen("home")
    object Profile : MrScreen("profile")
    object Detailvivo : MrScreen("home/{vivoId}") {
        fun createRoute(vivoId: Long) = "home/$vivoId"
    }
}
