plugins {
    alias(libs.plugins.users.android.library)
    alias(libs.plugins.users.koin.android)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.google.secrets.gradle.plugin)
}

android {
    namespace = "com.users.core.network"
}

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}

dependencies {
    api(projects.core.model)
    implementation(projects.core.common)

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)

    testImplementation(libs.ktor.client.mock)
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
}