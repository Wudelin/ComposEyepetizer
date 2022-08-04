package com.wdl.feature.mine.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.wdl.core.navigation.EyeNavigationDestination
import com.wdl.feature.mine.MineRoute

// 我的路由
object MineNavigation : EyeNavigationDestination {
    override val route = "mine_route"
    override val destination = "mine_destination"
}

fun NavGraphBuilder.mineGraph(
    windowSizeClass: WindowSizeClass
) {
    composable(route = MineNavigation.route) {
        MineRoute(windowSizeClass)
    }
}