@file:OptIn(ExperimentalWasmDsl::class)

import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    kotlin("multiplatform") version "2.0.21"
    id("org.jetbrains.dokka") version "1.9.20"
    id("org.jetbrains.kotlinx.kover") version "0.8.3"
    `maven-publish`
    signing
}

repositories {
    mavenCentral()
}

kotlin {
    jvm()

    val otherTargets = listOf(
        js(IR) {
            nodejs()
            browser()
        },
        iosArm64(),
        iosX64(),
        iosSimulatorArm64(),
        macosX64(),
        macosArm64(),
        watchosArm64(),
        watchosX64(),
        watchosSimulatorArm64(),
        watchosDeviceArm64(),
        tvosArm64(),
        tvosX64(),
        tvosSimulatorArm64(),
        mingwX64(),
        linuxArm64(),
        linuxX64(),
        androidNativeArm32(),
        androidNativeArm64(),
        androidNativeX86(),
        androidNativeX64(),
        wasmJs {
            browser()
        },
        wasmWasi()
    )

    sourceSets {
        all {
            languageSettings.progressiveMode = true
        }

        val commonMain by getting

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val nativeJsMain by creating {
            dependsOn(commonMain)
        }

        val nativeJsTest by creating {
            dependsOn(commonTest)
        }

        configure(otherTargets) {
            compilations["main"].defaultSourceSet.dependsOn(nativeJsMain)
            compilations["test"].defaultSourceSet.dependsOn(nativeJsTest)
        }
    }
}

val javadocJar by tasks.registering(Jar::class) {
    val dokkaHtml = tasks.named<DokkaTask>("dokkaHtml")
    dependsOn(dokkaHtml)
    archiveClassifier = "javadoc"
    from(dokkaHtml.get().outputDirectory)
}

signing {
    val signingKey: String? by project
    val signingPassword: String? by project
    useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications)
}

val isReleaseBuild get() = !version.toString().endsWith("SNAPSHOT")

tasks.withType<Sign>().configureEach {
    onlyIf { isReleaseBuild }
}

publishing {
    repositories {
        maven {
            val snapshotRepositoryUrl: String by project
            val releaseRepositoryUrl: String by project
            val repositoryUrl = if (isReleaseBuild) releaseRepositoryUrl else snapshotRepositoryUrl

            url = uri(repositoryUrl)

            val repositoryUsername: String? by project
            val repositoryPassword: String? by project

            credentials {
                username = repositoryUsername.orEmpty()
                password = repositoryPassword.orEmpty()
            }
        }
    }

    publications.withType<MavenPublication>().configureEach {
        artifact(javadocJar.get())

        pom {
            name = "JavaMath2KMP"
            description = "A Kotlin Multiplatform port of Java math functions not included in the standard library"
            url = "https://github.com/erikc5000/javamath2kmp"
            licenses {
                license {
                    name = "The Apache License, Version 2.0"
                    url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
                    distribution = "repo"
                }
            }
            developers {
                developer {
                    id = "erikc5000"
                    name = "Erik Christensen"
                }
            }
            scm {
                connection = "scm:git:https://github.com/erikc5000/javamath2kmp.git"
                url = "https://github.com/erikc5000/javamath2kmp"
            }
        }
    }
}

tasks.withType<AbstractPublishToMaven>().configureEach {
    val signingTasks = tasks.withType<Sign>()
    mustRunAfter(signingTasks)
}

tasks.withType<KotlinJvmCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_11)
    }
}

tasks.withType<AbstractTestTask>().configureEach {
    testLogging {
        events = setOf(TestLogEvent.FAILED)
        exceptionFormat = TestExceptionFormat.FULL
        showStackTraces = true
    }
}
