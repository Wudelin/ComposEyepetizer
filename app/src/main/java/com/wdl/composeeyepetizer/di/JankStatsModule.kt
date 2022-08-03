package com.wdl.composeeyepetizer.di

import android.app.Activity
import android.util.Log
import android.view.Window
import androidx.metrics.performance.JankStats
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * https://developer.android.com/studio/profile/jankstats
 */
const val TAG = "JankStats"

@Module
@InstallIn(ActivityComponent::class)
object JankStatsModule {

    @Provides
    fun providersOnFrameListener() = JankStats.OnFrameListener { frameData ->
        if (frameData.isJank) {
            Log.e(TAG, frameData.toString())
        }
    }

    @Provides
    fun providersWindow(activity: Activity) = activity.window

    @Provides
    fun providersDefaultExecutor() = Dispatchers.Default.asExecutor()

    @Provides
    fun providersJankStats(
        window: Window,
        executor: Executor,
        frameListener: JankStats.OnFrameListener
    ) = JankStats.createAndTrack(window, executor, frameListener)
}