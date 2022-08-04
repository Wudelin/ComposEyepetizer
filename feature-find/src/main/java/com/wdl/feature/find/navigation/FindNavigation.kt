package com.wdl.feature.find.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.wdl.core.navigation.EyeNavigationDestination
import com.wdl.feature.find.FindRoute

// 发现路由
object FindNavigation : EyeNavigationDestination {
    override val route = "find_route"
    override val destination = "find_destination"
}

fun NavGraphBuilder.findGraph(
    windowSizeClass: WindowSizeClass
) {
    composable(route = FindNavigation.route) {
        FindRoute(windowSizeClass)
    }
}