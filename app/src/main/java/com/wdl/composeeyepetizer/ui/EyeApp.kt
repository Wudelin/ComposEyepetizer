package com.wdl.composeeyepetizer.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.wdl.composeeyepetizer.navigation.EyeNavHost
import com.wdl.composeeyepetizer.navigation.TopLevelDestination
import com.wdl.composeeyepetizer.ui.theme.ComposeEyepetizerTheme
import com.wdl.core.design.compoent.EyeBottomNavigationItem
import com.wdl.core.design.compoent.EyeNavigation
import com.wdl.core.design.icon.Icon

@OptIn(ExperimentalComposeUiApi::class, ExperimentalLayoutApi::class)
@Composable
fun EyeApp(
    windowSizeClass: WindowSizeClass,
    appState: EyeAppState = rememberEyeAppState(windowSizeClass)
) {
    ComposeEyepetizerTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Scaffold(
                modifier = Modifier.semantics {
                    testTagsAsResourceId = true
                },
                bottomBar = {
                    if (appState.shouldShowBottomBar) {
                        EyeBottomBar(
                            destinations = appState.topLevelDestinations,
                            onNavigateToDestination = appState::navigate,
                            currentDestination = appState.currentDestination
                        )
                    }
                }
            ) { padding ->
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(
                            WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal)
                        )
                ) {
                    EyeNavHost(
                        navController = appState.navController,
                        onBackClick = appState::onBackClick,
                        onNavigateToDestination = appState::navigate,
                        windowSizeClass = appState.windowSizeClass,
                        modifier = Modifier
                            .padding(padding)
                            .consumedWindowInsets(padding)
                    )
                }
            }
        }
    }
}

@Composable
fun EyeBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?
) {
    Surface(color = MaterialTheme.colors.surface) {
        EyeNavigation(
            modifier = Modifier.windowInsetsPadding(
                WindowInsets.safeDrawing.only(
                    WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom
                )
            )
        ) {
            destinations.forEach { destination ->
                val selected =
                    currentDestination?.hierarchy?.any { it.route == destination.route } == true
                EyeBottomNavigationItem(
                    selected = selected,
                    onClick = { onNavigateToDestination(destination) },
                    icon = {
                        when (val icon = destination.selectedIcon) {
                            is Icon.ImageVectorIcon -> Icon(
                                imageVector = icon.imageVector,
                                contentDescription = null,
                                modifier = Modifier.size(if (selected) 24.dp else 0.dp)
                            )
                            else -> {}
                        }
                    },
                    label = if (selected) null else {
                        {
                            Text(
                                text = stringResource(destination.iconTextId)
                            )
                        }
                    },
                    alwaysShowLabel = !selected
                )
            }
        }
    }
}