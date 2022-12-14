package com.wdl.composeeyepetizer.navigation

import com.wdl.core.design.icon.Icon
import com.wdl.core.navigation.EyeNavigationDestination

data class TopLevelDestination(
    override val route: String,
    override val destination: String,
    val selectedIcon: Icon,
    val iconTextId: Int
) : EyeNavigationDestination
