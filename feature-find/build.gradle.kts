plugins {
    id("eyepetizer.android.library")
    id("eyepetizer.android.feature")
    id("eyepetizer.android.library.compose")
    id("eyepetizer.android.library.jacoco")
    id("dagger.hilt.android.plugin")
    id("eyepetizer.spotless")
}

dependencies {
    implementation(libs.kotlinx.datetime)
}