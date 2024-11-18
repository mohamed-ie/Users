plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradle)
    compileOnly(libs.kotlin.gradle)
    compileOnly(libs.ksp.gradle)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "users.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        register("androidLibrary") {
            id = "users.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidLibraryCompose") {
            id = "users.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        register("androidApplication") {
            id = "users.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("koin") {
            id = "users.koin.android"
            implementationClass = "AndroidKoinConventionPlugin"
        }

        register("androidFeature") {
            id = "users.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
    }
}