plugins {
    alias(libs.plugins.users.android.library.compose)
}

android {
    namespace = "com.users.core.ui"
}

dependencies {
    api(projects.core.desginSystem)
    api(libs.androidx.navigation.compose)
    implementation(projects.core.model)
}