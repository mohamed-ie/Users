plugins {
    alias(libs.plugins.users.android.library)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.users.core.model"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}