
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("eyepetizer.android.library")
    id("eyepetizer.android.library.jacoco")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    alias(libs.plugins.ksp)
    id("eyepetizer.spotless")
}

dependencies {
    api(libs.androidx.navigation.compose)
    api(libs.androidx.hilt.navigation.compose)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

}