package com.wdl.composeeyepetizer.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.wdl.core.navigation.EyeNavigationDestination
import com.wdl.feature.find.navigation.findGraph
import com.wdl.feature.home.navigation.HomeNavigation
import com.wdl.feature.home.navigation.homeGraph
import com.wdl.feature.mine.navigation.mineGraph
import com.wdl.feature.square.navigation.squareGraph

@Composable
fun EyeNavHost(
    navController: NavHostController,
    onBackClick: () -> Unit,
    onNavigateToDestination: (EyeNavigationDestination,String) -> Unit,
    windowSizeClass: WindowSizeClass,
    modifier: Modifier,
    startDestination: String = HomeNavigation.route
) {
    NavHost(navController = navController, startDestination = startDestination, modifier = modifier){
        homeGraph(windowSizeClass)

        squareGraph(windowSizeClass)

        findGraph(windowSizeClass)

        mineGraph(windowSizeClass)
    }
}