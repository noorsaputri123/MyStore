package com.rie.mystore.ui.screen

import androidx.compose.ui.graphics.vector.ImageVector

data class MrNavigation(
    val title: String,
    val icon: ImageVector,
    val screen: MrScreen,
    val contentDescription: String
)
