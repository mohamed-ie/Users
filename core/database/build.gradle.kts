plugins {
    alias(libs.plugins.users.android.library)
    alias(libs.plugins.users.koin.android)
}

android {
    namespace = "com.users.core.database"
}

dependencies {
    api(projects.core.model)

    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.test.runner)
}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}