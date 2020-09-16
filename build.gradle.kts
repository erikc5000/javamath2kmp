import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform") version "1.4.10"
    id("org.jetbrains.dokka") version "1.4.0"
    `maven-publish`
    signing
}

repositories {
    jcenter()
}

val ideaActive get() = System.getProperty("idea.active") == "true"

kotlin {
    jvm()

    val otherTargets = if (ideaActive) {
        listOf(mingwX64("nativeJs"))
    } else {
        listOf(
            js {
                nodejs()
                browser()
            },
            iosArm32(),
            iosArm64(),
            iosX64(),
            macosX64(),
            watchosArm64(),
            watchosX86(),
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
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
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

            val jsTest by getting {
                dependencies {
                    implementation(kotlin("test-js"))
                }
            }

            configure(otherTargets) {
                compilations["main"].defaultSourceSet.dependsOn(nativeJsMain)
                compilations["test"].defaultSourceSet.dependsOn(nativeJsTest)
            }
        }
    }
}

val isReleaseBuild get() = !version.toString().endsWith("SNAPSHOT")

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

        val pomName: String by project
        val pomDescription: String by project
        val pomScmUrl: String? by project
        val pomUrl: String? by project
        val pomScmConnection: String? by project
        val pomLicenseName: String? by project
        val pomLicenseUrl: String? by project
        val pomLicenseDist: String? by project
        val pomDeveloperId: String? by project
        val pomDeveloperName: String? by project
        val pomArtifactId: String? by project

        if (pomArtifactId != null) {
            artifactId = pomArtifactId
        }

        pom {
            name.set(pomName)
            description.set(pomDescription)
            url.set(pomUrl)
            licenses {
                license {
                    name.set(pomLicenseName)
                    url.set(pomLicenseUrl)
                    distribution.set(pomLicenseDist)
                }
            }
            developers {
                developer {
                    id.set(pomDeveloperId)
                    name.set(pomDeveloperName)

                }
            }
            scm {
                connection.set(pomScmConnection)
                url.set(pomScmUrl)
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
