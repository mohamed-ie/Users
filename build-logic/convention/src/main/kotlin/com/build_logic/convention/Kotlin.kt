package com.build_logic.convention

import com.build_logic.convention.utils.versionInt
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension

fun Project.configureKotlin() {
    configureKotlinJvm()
}

internal fun Project.configureKotlinJvm() {
    kotlinExtension.jvmToolchain(versionInt("jdk"))
}