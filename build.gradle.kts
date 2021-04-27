import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    kotlin("multiplatform") version "1.5.0"
    id("org.jetbrains.dokka") version "1.4.32"
    `maven-publish`
    signing
    jacoco
}

repositories {
    mavenCentral()
    maven(url = "https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
}

val ideaActive get() = System.getProperty("idea.active") == "true"

kotlin {
    jvm()

    val otherTargets = if (ideaActive) {
        listOf(mingwX64("nativeJs"))
    } else {
        listOf(
            js(BOTH) {
                nodejs()
                browser()
            },
            iosArm32(),
            iosArm64(),
            iosX64(),
            macosX64(),
            watchosArm64(),
            watchosX86(),
            watchosX64(),
            tvosArm64(),
            tvosX64(),
            mingwX64(),
            mingwX86(),
            linuxArm64(),
            linuxArm32Hfp(),
            linuxX64()
        )
    }

    sourceSets {
        all {
            languageSettings.progressiveMode = true
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        if (!ideaActive) {
            val commonMain by getting

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
}

val javadocJar by tasks.registering(Jar::class) {
    val dokkaHtml = tasks.named<DokkaTask>("dokkaHtml")
    dependsOn(dokkaHtml)
    archiveClassifier.set("javadoc")
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
            name.set("JavaMath2KMP")
            description.set("A Kotlin Multiplatform port of Java math functions not included in the standard library")
            url.set("https://github.com/erikc5000/javamath2kmp")
            licenses {
                license {
                    name.set("The Apache License, Version 2.0")
                    url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    distribution.set("repo")
                }
            }
            developers {
                developer {
                    id.set("erikc5000")
                    name.set("Erik Christensen")
                }
            }
            scm {
                connection.set("scm:git:https://github.com/erikc5000/javamath2kmp.git")
                url.set("https://github.com/erikc5000/javamath2kmp")
            }
        }
    }
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

tasks.withType(AbstractTestTask::class).configureEach {
    testLogging {
        events = setOf(TestLogEvent.FAILED)
        exceptionFormat = TestExceptionFormat.FULL
        showStackTraces = true
    }
}

tasks.register<JacocoReport>("testCoverage") {
    dependsOn(tasks.named("jvmTest"))

    classDirectories.setFrom(
        fileTree("${buildDir}/classes/kotlin/jvm/") {
            exclude("**/*Test*.*")
        }
    )

    sourceDirectories.setFrom(kotlin.sourceSets["commonMain"].kotlin.sourceDirectories)
    executionData.setFrom("${buildDir}/jacoco/jvmTest.exec")
}
