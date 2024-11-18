plugins {
    alias(libs.plugins.users.android.feature)
    alias(libs.plugins.users.koin.android)
}

android {
    namespace = "com.users.feature.users"
}

dependencies {
    implementation(projects.core.data)
}