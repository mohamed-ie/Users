plugins {
    alias(libs.plugins.users.android.library)
    alias(libs.plugins.users.koin.android)
}

android {
    namespace = "com.users.core.common"
}

dependencies {
    api(libs.kotlinx.coroutines.core)
}