dependencies {
    //implementation("androidx.compose.ui:ui-unit-android:1.8.2")
    implementation(Compose.ui)
    implementation(Compose.material)
    implementation(Compose.activityCompose)
    implementation(Compose.runtime)
    implementation(Compose.compiler)
    implementation(Compose.viewModelCompose)
}
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}


apply(from = "$rootDir/base-module.gradle")
android {
    namespace = "com.musscoding.core_ui"
}