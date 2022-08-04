package com.wdl.core.ui

import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalView
import androidx.metrics.performance.PerformanceMetricsState
import kotlinx.coroutines.CoroutineScope

// 从当前view中记住PerformanceMetricsState，改变时重新重组
@Composable
fun rememberMetricsStateHolder(): PerformanceMetricsState.MetricsStateHolder {
    val localView = LocalView.current

    return remember(localView) {
        PerformanceMetricsState.getForHierarchy(localView)
    }
}

@Composable
fun JankMetericEffect(
    vararg keys: Any?,
    reportMetric: suspend CoroutineScope.(state: PerformanceMetricsState.MetricsStateHolder) -> Unit
) {
    val metric = rememberMetricsStateHolder()

    LaunchedEffect(metric, *keys) {
        reportMetric(metric)
    }
}

@Composable
fun JankMetericDisposableEffect(
    vararg keys: Any?,
    reportMetric: DisposableEffectScope.(state: PerformanceMetricsState.MetricsStateHolder) -> DisposableEffectResult
) {
    val metric = rememberMetricsStateHolder()

    DisposableEffect(metric, *keys) {
        reportMetric(this, metric)
    }
}

@Composable
fun TrackScrollJank(scrollableState: ScrollableState, stateName: String) {
    JankMetericEffect(scrollableState) { state: PerformanceMetricsState.MetricsStateHolder ->
        snapshotFlow { scrollableState.isScrollInProgress }.collect { isScrollInProgress ->
            state.state?.apply {
                if (isScrollInProgress) {
                    addState(stateName, "Scrolling=true")
                } else {
                    removeState(stateName)
                }
            }
        }
    }
}