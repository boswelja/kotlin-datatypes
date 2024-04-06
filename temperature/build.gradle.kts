import org.jetbrains.dokka.gradle.DokkaTaskPartial
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import java.net.URL

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)

    alias(libs.plugins.detekt)

    alias(libs.plugins.dokka)

    id("maven-publish")
    id("signing")
}

group = findProperty("group")!!
version = findProperty("version")!!

android {
    namespace = "com.boswelja.temperature"

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
                implementation(libs.ionspin.bignum)
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

signing {
    val signingKey: String? by project
    val signingPassword: String? by project
    useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications)
}

publishing {
    repositories {
        val githubUsername = findProperty("githubUsername")?.toString()
        val githubToken = findProperty("githubToken")?.toString()
        if (githubUsername != null && githubToken != null) {
            maven("https://maven.pkg.github.com/boswelja/kotlin-datatypes") {
                name = "github"
                credentials {
                    username = githubUsername
                    password = githubToken
                }
            }
        }
        val ossrhUsername = findProperty("ossrhUsername")?.toString()
        val ossrhPassword = findProperty("ossrhPassword")?.toString()
        if (ossrhUsername != null && ossrhPassword != null) {
            maven("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/") {
                name = "oss"
                credentials {
                    username = ossrhUsername
                    password = ossrhPassword
                }
            }
        }
    }

    publications.withType<MavenPublication> {
        pom {
            name = "temperature"
            description = "A Temperature class that allows you to convert between any well-known temperature unit"
            url = "https://github.com/boswelja/kotlin-datatypes/tree/main/temperature"
            licenses {
                license {
                    name = "MIT"
                    url = "https://github.com/boswelja/kotlin-datatypes/blob/main/LICENSE"
                }
            }
            developers {
                developer {
                    id = "boswelja"
                    name = "Jack Boswell (boswelja)"
                    email = "boswelja@outlook.com"
                    url = "https://github.com/boswelja"
                }
            }
            scm {
                connection.set("scm:git:github.com/boswelja/kotlin-datatypes.git")
                developerConnection.set("scm:git:ssh://github.com/boswelja/kotlin-datatypes.git")
                url.set("https://github.com/boswelja/kotlin-datatypes")
            }
        }
    }
}

tasks.withType<DokkaTaskPartial>().configureEach {
    dokkaSourceSets.configureEach {
        includes.from("MODULE.md")
        sourceLink {
            localDirectory.set(projectDir.resolve("src"))
            remoteUrl.set(URL("https://github.com/boswelja/kotlin-datatypes/tree/main/temperature/src"))
            remoteLineSuffix.set("#L")
        }
    }
}
