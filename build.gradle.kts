plugins {
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.detekt) apply false

    id("org.jetbrains.dokka")
}

repositories {
    mavenCentral()
}
