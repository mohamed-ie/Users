plugins {
    alias(libs.plugins.users.android.feature)
    alias(libs.plugins.users.koin.android)
}

android {
    namespace = "com.users.feature.likes"
}

dependencies {
    implementation(projects.core.data)
}