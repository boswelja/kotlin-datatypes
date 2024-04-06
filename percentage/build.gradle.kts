import org.jetbrains.dokka.gradle.DokkaTaskPartial
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import java.net.URL

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)

    alias(libs.plugins.detekt)

    id("com.boswelja.publish")
}

group = findProperty("group")!!
version = findProperty("version")!!

android {
    namespace = "com.boswelja.percentage"

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
    jvmToolchain(17)

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
            }
        }
        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
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
    description = "A Bitrate class that allows you to convert between any unit of data transfer speed"
    repositoryUrl = "https://github.com/boswelja/kotlin-datatypes"
    license = "MIT"
}

tasks.withType<DokkaTaskPartial>().configureEach {
    dokkaSourceSets.configureEach {
        includes.from("MODULE.md")
        sourceLink {
            localDirectory.set(projectDir.resolve("src"))
            remoteUrl.set(URL("${publish.repositoryUrl.get()}/tree/main/${project.name}/src"))
            remoteLineSuffix.set("#L")
        }
    }
}
