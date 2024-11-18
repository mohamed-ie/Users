package com.build_logic.convention

import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.build_logic.convention.utils.version
import com.build_logic.convention.utils.versionInt
import org.gradle.api.Project

fun Project.configureKotlinAndroidApplication(
    baseAppModuleExtension: BaseAppModuleExtension
) {
    baseAppModuleExtension.apply {
        compileSdk = versionInt("android-targetSdk")

        defaultConfig {
            targetSdk = versionInt("android-targetSdk")
            versionCode = versionInt("versionCode")
            versionName = version("versionName")
        }

        buildTypes {
            release {
                isMinifyEnabled = true
                isShrinkResources = true
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
    }

    configureKotlinAndroid(baseAppModuleExtension)
}

fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.apply {
        compileSdk = versionInt("android-compileSdk")

        defaultConfig {
            minSdk = versionInt("android-minSdk")
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        buildFeatures {
            buildConfig = true
        }
    }
    configureKotlin()
}