package com.wdl.composeeyepetizer.navigation

import android.graphics.drawable.Icon
import com.wdl.core.navigation.EyeNavigationDestination

data class TopLevelDestination(
    override val route: String,
    override val destination: String,
    val selectedIcon: Icon,
    val iconTextId: Int
) : EyeNavigationDestination
