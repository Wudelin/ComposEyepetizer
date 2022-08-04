package com.wdl.composeeyepetizer.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FindInPage
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.HomeMini
import androidx.compose.material.icons.filled.Square
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.util.trace
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.wdl.feature.home.R as homeR
import com.wdl.feature.square.R as squareR
import com.wdl.feature.find.R as findR
import com.wdl.feature.mine.R as mineR
import com.wdl.composeeyepetizer.navigation.TopLevelDestination
import com.wdl.core.design.icon.Icon
import com.wdl.core.navigation.EyeNavigationDestination
import com.wdl.core.ui.JankMetericDisposableEffect
import com.wdl.feature.find.navigation.FindNavigation
import com.wdl.feature.home.navigation.HomeNavigation
import com.wdl.feature.mine.navigation.MineNavigation
import com.wdl.feature.square.navigation.SquareNavigation

// 自定义remember state
@Stable
class EyeAppState(
    val navController: NavHostController,
    val windowSizeClass: WindowSizeClass
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val shouldShowBottomBar: Boolean
        get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact ||
                windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact

    val shouldShowNavRail: Boolean
        get() = !shouldShowBottomBar

    val topLevelDestinations: List<TopLevelDestination> = listOf(
        TopLevelDestination(
            route = HomeNavigation.route,
            destination = HomeNavigation.destination,
            selectedIcon = Icon.ImageVectorIcon(Icons.Default.Home),
            iconTextId = homeR.string.home
        ),
        TopLevelDestination(
            route = SquareNavigation.route,
            destination = SquareNavigation.destination,
            selectedIcon = Icon.ImageVectorIcon(Icons.Default.Square),
            iconTextId = squareR.string.square
        ),
        TopLevelDestination(
            route = FindNavigation.route,
            destination = FindNavigation.destination,
            selectedIcon = Icon.ImageVectorIcon(Icons.Default.FindInPage),
            iconTextId = findR.string.find
        ),
        TopLevelDestination(
            route = MineNavigation.route,
            destination = MineNavigation.destination,
            selectedIcon = Icon.ImageVectorIcon(Icons.Default.HomeMini),
            iconTextId = mineR.string.mine
        )
    )

    // 导航实际方法
    fun navigate(destination: EyeNavigationDestination, route: String? = null) {
        trace("Navigation: $destination") {
            if (destination is TopLevelDestination) {
                navController.navigate(route ?: destination.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    // 避免同一个目标创建多个
                    launchSingleTop = true
                    // 状态恢复
                    restoreState = true
                }
            } else {
                navController.navigate(route ?: destination.route)
            }
        }
    }

    fun onBackClick() {
        navController.popBackStack()
    }
}

// Jank跟踪路由变换
@Composable
private fun NavigationTrackingSideEffect(navController: NavHostController) {
    JankMetericDisposableEffect(navController) { metricsHolder ->
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            destination.route?.let { metricsHolder.state?.addState("Navigation", it) }
        }

        navController.addOnDestinationChangedListener(listener)

        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }
}