package com.build_logic.convention

import com.android.build.api.dsl.CommonExtension

fun configureKotlinAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.buildFeatures {
        compose = true
    }
}
