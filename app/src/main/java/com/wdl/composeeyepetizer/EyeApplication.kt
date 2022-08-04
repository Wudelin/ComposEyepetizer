package com.wdl.composeeyepetizer

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.SvgDecoder
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EyeApplication : Application(), ImageLoaderFactory {
    override fun newImageLoader() =
        ImageLoader.Builder(this)
            .components {
                add(SvgDecoder.Factory())
            }.build()
}