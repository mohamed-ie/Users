plugins {
    alias(libs.plugins.users.android.library)
    alias(libs.plugins.users.koin.android)
}

android {
    namespace = "com.users.core.data"
}

dependencies {
    api(projects.core.model)

    implementation(projects.core.common)
    implementation(projects.core.database)
    implementation(projects.core.network)

    implementation(libs.kotlinx.coroutines.core)

    testImplementation(projects.core.test)
}