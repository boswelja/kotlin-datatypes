import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import java.net.URI

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)

    alias(libs.plugins.detekt)

    alias(libs.plugins.dokka)
    id("com.boswelja.publish")
}

android {
    namespace = "com.boswelja.length"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    lint {
        sarifReport = true
        htmlReport = false
    }
}

repositories {
    google()
    mavenCentral()
}

kotlin {
    jvmToolchain(21)

    // Android
    androidTarget {
        publishLibraryVariants("release")
    }

    // JVM
    jvm()

    // Apple
    iosArm64()
    iosX64()
    macosX64()
    macosArm64()
    tvosX64()
    tvosArm64()
    watchosArm32()
    watchosArm64()

    // Windows
    mingwX64()

    // Linux
    linuxArm64()
    linuxX64()

    // WASM
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlin("stdlib"))
                implementation(libs.ionspin.bignum)
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

detekt {
    buildUponDefaultConfig = true
    config.setFrom("$rootDir/config/detekt.yml")
    basePath = rootDir.absolutePath
}

publish {
    description = "A Length class that allows you to convert between any unit of length"
    repositoryUrl = "https://github.com/boswelja/kotlin-datatypes"
    license = "MIT"
}

dokka {
    dokkaSourceSets.configureEach {
        includes.from("MODULE.md")
        sourceLink {
            localDirectory.set(projectDir.resolve("src"))
            remoteUrl.set(URI("${publish.repositoryUrl.get()}/tree/main/${project.name}/src"))
            remoteLineSuffix.set("#L")
        }
    }
}