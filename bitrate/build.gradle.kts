import org.jetbrains.dokka.gradle.DokkaTaskPartial
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
    namespace = "com.boswelja.bitrate"

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

    publishing {
        singleVariant("release") {
            withJavadocJar()
            withSourcesJar()
        }
    }
}

kotlin {
    androidTarget {
        publishLibraryVariants("release")
    }
    jvm()
    jvmToolchain(17)

    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlin("stdlib"))
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
            name = "bitrate"
            description = "A Bitrate class that allows you to convert between any unit of data transfer speed"
            url = "https://github.com/boswelja/kotlin-datatypes/tree/main/bitrate"
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
            remoteUrl.set(URL("https://github.com/boswelja/kotlin-datatypes/tree/main/bitrate/src"))
            remoteLineSuffix.set("#L")
        }
    }
}
