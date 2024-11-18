plugins {
    alias(libs.plugins.users.android.application.compose)
    alias(libs.plugins.users.koin.android)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.users.app"
    defaultConfig {
        applicationId = "com.users.app"
    }
}

dependencies {
    implementation(projects.core.ui)

    implementation(projects.feature.users)
    implementation(projects.feature.likes)

    implementation(projects.core.common)
    implementation(projects.core.data)
    implementation(projects.core.database)
    implementation(projects.core.network)

    implementation(libs.koin.android)
    implementation(libs.kotlinx.serialization.json)
}