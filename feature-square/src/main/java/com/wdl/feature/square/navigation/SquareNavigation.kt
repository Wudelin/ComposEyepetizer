package com.wdl.feature.square.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.wdl.core.navigation.EyeNavigationDestination
import com.wdl.feature.square.SquareRoute

// 发现路由
object SquareNavigation : EyeNavigationDestination {
    override val route = "square_route"
    override val destination = "square_destination"
}

fun NavGraphBuilder.squareGraph(
    windowSizeClass: WindowSizeClass
) {
    composable(route = SquareNavigation.route) {
        SquareRoute(windowSizeClass)
    }
}