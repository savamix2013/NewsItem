import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

// Читаємо NEWS_API_KEY з local.properties
val localProperties = Properties().apply {
    val localPropertiesFile = rootProject.file("local.properties")

    if (localPropertiesFile.exists()) {
        localPropertiesFile.inputStream().use {
            load(it)
        }
    }
}

val newsApiKey = localProperties.getProperty("NEWS_API_KEY") ?: ""

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {

    android {
        namespace = "com.example.newsappkmp.shared"

        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }

        androidResources {
            enable = true
        }

        withHostTest {
            isIncludeAndroidResources = true
        }

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner =
                "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    sourceSets {

        // Android
        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.compose.uiTooling)

            implementation(libs.firebase.messaging)

            // Ktor для Android
            implementation(libs.ktor.client.okhttp)
        }

        // Shared / Common
        commonMain.dependencies {

            // Ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.logging)

            // Compose
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)

            // Lifecycle / ViewModel
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            // Kotlin
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.coroutines.core)
        }

        // Tests
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

abstract class GenerateBuildConfigTask : DefaultTask() {
    @get:Input
    abstract val apiKey: Property<String>

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @TaskAction
    fun generate() {
        val file = outputDir.get().file("com/example/newsappkmp/shared/BuildConfig.kt").asFile
        file.parentFile.mkdirs()
        val key = apiKey.get().replace("\\", "\\\\").replace("\"", "\\\"")
        file.writeText(
            "package com.example.newsappkmp.shared\n\n" +
            "object BuildConfig {\n" +
            "    const val NEWS_API_KEY: String = \"$key\"\n" +
            "}\n"
        )
    }
}

val generateBuildConfig = tasks.register("generateBuildConfig", GenerateBuildConfigTask::class.java) {
    apiKey.set(newsApiKey)
    outputDir.set(layout.buildDirectory.dir("generated/source/buildConfig/main/kotlin"))
}

kotlin.sourceSets.getByName("commonMain").kotlin.srcDir(generateBuildConfig.map { it.outputDir })

// Compose tooling для Android
dependencies {
    androidRuntimeClasspath(libs.compose.uiTooling)
}
