plugins {
    alias(libs.plugins.users.android.library)
    alias(libs.plugins.users.koin.android)
}

android {
    namespace = "com.users.core.test"
}

dependencies {
    api(kotlin("test"))
    api(projects.core.model)
    api(projects.core.database)
    api(projects.core.network)
    api(libs.junit)
    api(libs.kotlinx.coroutines.test)
    api(libs.mockito.core)
    api(libs.mockito.kotlin)
}