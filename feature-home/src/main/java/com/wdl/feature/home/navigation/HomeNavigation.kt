package com.wdl.feature.home.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.wdl.core.navigation.EyeNavigationDestination
import com.wdl.feature.home.HomeRoute

// 首页路由
object HomeNavigation : EyeNavigationDestination {
    override val route = "home_route"
    override val destination = "home_destination"
}

fun NavGraphBuilder.homeGraph(
    windowSizeClass:WindowSizeClass
){
    composable(route = HomeNavigation.route){
        HomeRoute(windowSizeClass)
    }
}