package com.wdl.core.design.compoent

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun EyeNavigation(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = NavigationDefaults.NavigationBackgroundColor,
        contentColor = NavigationDefaults.navigationContentColor(),
        elevation = 0.1.dp,
        content = content
    )
}

@Composable
fun RowScope.EyeBottomNavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    enable: Boolean = true,
    alwaysShowLabel: Boolean = true,
    label: @Composable (() -> Unit)? = null
) {
    BottomNavigationItem(
        selectedContentColor = Color.Black,
        unselectedContentColor = Color.Black.copy(alpha = ContentAlpha.high),
        selected = selected,
        enabled = enable,
        alwaysShowLabel = alwaysShowLabel,
        label = label,
        onClick = onClick,
        icon = icon
    )
}

object NavigationDefaults {
    val NavigationBackgroundColor = Color.Transparent

    @Composable
    fun navigationContentColor() = Color.White
}