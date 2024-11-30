@file:Suppress("UnstableApiUsage")

includeBuild("build-logic")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Users"
include(":app")
include(":core:network")
include(":core:model")
include(":core:common")
include(":core:test")
include(":core:data")
include(":core:database")
include(":core:ui")
include(":core:desgin_system")
include(":feature:users")
include(":feature:likes")